import { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { fetchBlogById, deleteBlog } from '@/lib/api';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger } from '@/components/ui/alert-dialog';

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

const BlogPostDetail = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [blog, setBlog] = useState<Blog | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadBlog = async () => {
      if (!id) {
        setError("Blog ID is missing.");
        setLoading(false);
        return;
      }
      try {
        const data = await fetchBlogById(id);
        setBlog(data);
      } catch (err) {
        setError("Failed to fetch blog post.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    loadBlog();
  }, [id]);

  const handleDelete = async () => {
    if (!id) return;
    try {
      await deleteBlog(id);
      navigate('/'); // Redirect to home page after deletion
    } catch (err) {
      setError("Failed to delete blog post.");
      console.error(err);
    }
  };

  if (loading) {
    return <div className="text-center text-xl">Loading blog post...</div>;
  }

  if (error) {
    return <div className="text-center text-xl text-red-500">Error: {error}</div>;
  }

  if (!blog) {
    return <div className="text-center text-xl">Blog post not found.</div>;
  }

  return (
    <div className="container mx-auto p-4">
      <Card>
        <CardHeader>
          <CardTitle className="text-3xl font-bold mb-2">{blog.title}</CardTitle>
          <CardDescription className="text-sm text-gray-500">
            {new Date(blog.createdAt).toLocaleDateString()} - {blog.category.name}
          </CardDescription>
        </CardHeader>
        <CardContent>
          <p className="text-lg leading-relaxed whitespace-pre-wrap">{blog.content}</p>
          <div className="mt-6 flex space-x-4">
            <Link to={`/edit/${blog.id}`}>
              <Button>Edit</Button>
            </Link>
            <AlertDialog>
              <AlertDialogTrigger asChild>
                <Button variant="destructive">Delete</Button>
              </AlertDialogTrigger>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
                  <AlertDialogDescription>
                    This action cannot be undone. This will permanently delete your
                    blog post and remove its data from our servers.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <AlertDialogFooter>
                  <AlertDialogCancel>Cancel</AlertDialogCancel>
                  <AlertDialogAction onClick={handleDelete}>Continue</AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default BlogPostDetail;