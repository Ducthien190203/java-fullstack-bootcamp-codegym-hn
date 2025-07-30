import { useState, useEffect } from 'react';
import { fetchCategories, createCategory, updateCategory, deleteCategory } from '@/lib/api';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog';
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger } from '@/components/ui/alert-dialog';

interface Category {
  id: number;
  name: string;
}

const CategoryManagement = () => {
  const [categories, setCategories] = useState<Category[]>([]);
  const [newCategoryName, setNewCategoryName] = useState('');
  const [editingCategory, setEditingCategory] = useState<Category | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    loadCategories();
  }, []);

  const loadCategories = async () => {
    try {
      const data = await fetchCategories();
      setCategories(data);
    } catch (err) {
      setError("Failed to load categories.");
      console.error(err);
    }
  };

  const handleCreateCategory = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    if (!newCategoryName.trim()) {
      setError("Category name cannot be empty.");
      return;
    }
    try {
      await createCategory({ name: newCategoryName });
      setNewCategoryName('');
      loadCategories();
    } catch (err) {
      setError("Failed to create category.");
      console.error(err);
    }
  };

  const handleUpdateCategory = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    if (!editingCategory || !editingCategory.name.trim()) {
      setError("Category name cannot be empty.");
      return;
    }
    try {
      await updateCategory(editingCategory.id.toString(), { name: editingCategory.name });
      setEditingCategory(null);
      loadCategories();
    } catch (err) {
      setError("Failed to update category.");
      console.error(err);
    }
  };

  const handleDeleteCategory = async (id: number) => {
    try {
      await deleteCategory(id.toString());
      loadCategories();
    } catch (err) {
      setError("Failed to delete category.");
      console.error(err);
    }
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">Category Management</h1>

      <div className="mb-8">
        <h2 className="text-2xl font-semibold mb-4">Create New Category</h2>
        <form onSubmit={handleCreateCategory} className="flex space-x-2">
          <Input
            type="text"
            placeholder="New category name"
            value={newCategoryName}
            onChange={(e) => setNewCategoryName(e.target.value)}
            className="flex-1"
          />
          <Button type="submit">Create</Button>
        </form>
        {error && <p className="text-red-500 mt-2">{error}</p>}
      </div>

      <div>
        <h2 className="text-2xl font-semibold mb-4">Existing Categories</h2>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Name</TableHead>
              <TableHead className="text-right">Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {categories.map((category) => (
              <TableRow key={category.id}>
                <TableCell>{category.id}</TableCell>
                <TableCell>{category.name}</TableCell>
                <TableCell className="text-right">
                  <Dialog open={editingCategory?.id === category.id} onOpenChange={(open) => !open && setEditingCategory(null)}>
                    <DialogTrigger asChild>
                      <Button variant="outline" size="sm" onClick={() => setEditingCategory(category)}>Edit</Button>
                    </DialogTrigger>
                    <DialogContent>
                      <DialogHeader>
                        <DialogTitle>Edit Category</DialogTitle>
                        <DialogDescription>Make changes to your category here.</DialogDescription>
                      </DialogHeader>
                      <form onSubmit={handleUpdateCategory} className="space-y-4">
                        <div>
                          <Label htmlFor="edit-category-name">Category Name</Label>
                          <Input
                            id="edit-category-name"
                            value={editingCategory?.name || ''}
                            onChange={(e) => setEditingCategory(editingCategory ? { ...editingCategory, name: e.target.value } : null)}
                            required
                          />
                        </div>
                        {error && <p className="text-red-500">{error}</p>}
                        <DialogFooter>
                          <Button type="submit">Save changes</Button>
                        </DialogFooter>
                      </form>
                    </DialogContent>
                  </Dialog>
                  <AlertDialog>
                    <AlertDialogTrigger asChild>
                      <Button variant="destructive" size="sm" className="ml-2">Delete</Button>
                    </AlertDialogTrigger>
                    <AlertDialogContent>
                      <AlertDialogHeader>
                        <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
                        <AlertDialogDescription>
                          This action cannot be undone. This will permanently delete the
                          category.
                        </AlertDialogDescription>
                      </AlertDialogHeader>
                      <AlertDialogFooter>
                        <AlertDialogCancel>Cancel</AlertDialogCancel>
                        <AlertDialogAction onClick={() => handleDeleteCategory(category.id)}>Continue</AlertDialogAction>
                      </AlertDialogFooter>
                    </AlertDialogContent>
                  </AlertDialog>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default CategoryManagement;