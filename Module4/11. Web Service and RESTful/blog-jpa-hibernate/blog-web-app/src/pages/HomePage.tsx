import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { fetchBlogs, fetchCategories } from '@/lib/api';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Pagination, PaginationContent, PaginationItem, PaginationPrevious, PaginationLink, PaginationNext } from '@/components/ui/pagination';

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

interface Category {
  id: number;
  name: string;
}

const HomePage = () => {
  const [blogs, setBlogs] = useState<Blog[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [selectedCategory, setSelectedCategory] = useState<string | undefined>(undefined);
  const [searchTerm, setSearchTerm] = useState<string>('');

  useEffect(() => {
    const loadBlogs = async () => {
      try {
        const data = await fetchBlogs(currentPage, 10, 'createdAt', 'desc', selectedCategory ? parseInt(selectedCategory) : undefined, searchTerm);
        setBlogs(data.content);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error("Error fetching blogs:", error);
      }
    };

    const loadCategories = async () => {
      try {
        const data = await fetchCategories();
        setCategories(data);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };

    loadBlogs();
    loadCategories();
  }, [currentPage, selectedCategory, searchTerm]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleCategoryChange = (value: string) => {
    setSelectedCategory(value === 'all' ? undefined : value);
    setCurrentPage(0);
  };

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0);
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">Blog Posts</h1>

      <div className="flex space-x-4 mb-6">
        <Input
          type="text"
          placeholder="Search by title or content..."
          value={searchTerm}
          onChange={handleSearchChange}
          className="flex-1"
        />
        <Select onValueChange={handleCategoryChange} value={selectedCategory || 'all'}>
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Filter by Category" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">All Categories</SelectItem>
            {categories.map((category) => (
              <SelectItem key={category.id} value={category.id.toString()}>
                {category.name}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {blogs.map((blog) => (
          <Card key={blog.id} className="flex flex-col">
            <CardHeader>
              <CardTitle>
                <Link to={`/blog/${blog.id}`} className="hover:underline">
                  {blog.title}
                </Link>
              </CardTitle>
              <CardDescription className="text-sm text-gray-500">
                {new Date(blog.createdAt).toLocaleDateString()} - {blog.category.name}
              </CardDescription>
            </CardHeader>
            <CardContent className="flex-1">
              <p>{blog.content.substring(0, 150)}...</p>
            </CardContent>
          </Card>
        ))}
      </div>

      <Pagination className="mt-8">
        <PaginationContent>
          <PaginationItem>
            <PaginationPrevious onClick={() => handlePageChange(currentPage - 1)} className={currentPage === 0 ? "pointer-events-none opacity-50" : ""} />
          </PaginationItem>
          {[...Array(totalPages)].map((_, index) => (
            <PaginationItem key={index}>
              <PaginationLink onClick={() => handlePageChange(index)} isActive={currentPage === index}>
                {index + 1}
              </PaginationLink>
            </PaginationItem>
          ))}
          <PaginationItem>
            <PaginationNext onClick={() => handlePageChange(currentPage + 1)} className={currentPage === totalPages - 1 ? "pointer-events-none opacity-50" : ""} />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
    </div>
  );
};

export default HomePage;