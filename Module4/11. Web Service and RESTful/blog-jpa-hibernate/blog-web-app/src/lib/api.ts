const API_BASE_URL = 'http://localhost:8080/api'; // Assuming your backend runs on 8080

export const fetchBlogs = async (page = 0, size = 10, sortBy = 'createdAt', sortDir = 'desc', categoryId?: number, searchTerm?: string) => {
  let url = `${API_BASE_URL}/blogs?page=${page}&size=${size}&sortBy=${sortBy}&sortDir=${sortDir}`;
  if (categoryId) {
    url += `&categoryId=${categoryId}`;
  }
  if (searchTerm) {
    url += `&searchTerm=${searchTerm}`;
  }
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const fetchBlogById = async (id: string) => {
  const response = await fetch(`${API_BASE_URL}/blogs/${id}`);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const createBlog = async (blog: any) => {
  const response = await fetch(`${API_BASE_URL}/blogs`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(blog),
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const updateBlog = async (id: string, blog: any) => {
  const response = await fetch(`${API_BASE_URL}/blogs/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(blog),
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const deleteBlog = async (id: string) => {
  const response = await fetch(`${API_BASE_URL}/blogs/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const fetchCategories = async () => {
  const response = await fetch(`${API_BASE_URL}/categories`);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const createCategory = async (category: any) => {
  const response = await fetch(`${API_BASE_URL}/categories`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(category),
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const updateCategory = async (id: string, category: any) => {
  const response = await fetch(`${API_BASE_URL}/categories/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(category),
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const deleteCategory = async (id: string) => {
  const response = await fetch(`${API_BASE_URL}/categories/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};
