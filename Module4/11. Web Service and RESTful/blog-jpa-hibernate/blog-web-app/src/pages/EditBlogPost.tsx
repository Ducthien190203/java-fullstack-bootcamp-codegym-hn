import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { fetchBlogById, updateBlog, fetchCategories } from '@/lib/api';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';

interface Category {
  id: number;
  name: string;
}

interface Blog {
  id: string;
  title: string;
  content: string;
  createdAt: string;
  category: {
    id: number;
    name: string;
  };
}

const EditBlogPost = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [categoryId, setCategoryId] = useState<string | undefined>(undefined);
  const [categories, setCategories] = useState<Category[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadBlogAndCategories = async () => {
      if (!id) {
        setError("Blog ID is missing.");
        setLoading(false);
        return;
      }
      try {
        const blogData: Blog = await fetchBlogById(id);
        setTitle(blogData.title);
        setContent(blogData.content);
        setCategoryId(blogData.category.id.toString());

        const categoriesData = await fetchCategories();
        setCategories(categoriesData);
      } catch (err) {
        setError("Failed to load blog post or categories.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    loadBlogAndCategories();
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!id || !title || !content || !categoryId) {
      setError("Please fill in all fields.");
      return;
    }

    try {
      await updateBlog(id, {
        title,
        content,
        category: { id: parseInt(categoryId) },
      });
      navigate(`/blog/${id}`); // Redirect to blog detail page after update
    } catch (err) {
      setError("Failed to update blog post.");
      console.error(err);
    }
  };

  if (loading) {
    return <div className="text-center text-xl">Loading blog post...</div>;
  }

  if (error) {
    return <div className="text-center text-xl text-red-500">Error: {error}</div>;
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">Edit Blog Post</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <Label htmlFor="title">Title</Label>
          <Input id="title" value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div>
          <Label htmlFor="content">Content</Label>
          <Textarea id="content" value={content} onChange={(e) => setContent(e.target.value)} required rows={10} />
        </div>
        <div>
          <Label htmlFor="category">Category</Label>
          <Select onValueChange={setCategoryId} value={categoryId} required>
            <SelectTrigger>
              <SelectValue placeholder="Select a category" />
            </SelectTrigger>
            <SelectContent>
              {categories.map((category) => (
                <SelectItem key={category.id} value={category.id.toString()}>
                  {category.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        {error && <p className="text-red-500">{error}</p>}
        <Button type="submit">Update Post</Button>
      </form>
    </div>
  );
};

export default EditBlogPost;