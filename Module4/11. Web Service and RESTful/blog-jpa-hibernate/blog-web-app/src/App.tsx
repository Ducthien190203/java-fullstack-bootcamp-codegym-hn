import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import BlogPostDetail from './pages/BlogPostDetail';
import CreateBlogPost from './pages/CreateBlogPost';
import EditBlogPost from './pages/EditBlogPost';
import CategoryManagement from './pages/CategoryManagement';
import Navbar from './components/layout/Navbar';
import Sidebar from './components/layout/Sidebar';

function App() {
  return (
    <Router>
      <div className="flex min-h-screen bg-background text-foreground">
        <Sidebar />
        <div className="flex-1 flex flex-col">
          <Navbar />
          <main className="flex-1 p-4">
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/blog/:id" element={<BlogPostDetail />} />
              <Route path="/create" element={<CreateBlogPost />} />
              <Route path="/edit/:id" element={<EditBlogPost />} />
              <Route path="/categories" element={<CategoryManagement />} />
            </Routes>
          </main>
        </div>
      </div>
    </Router>
  );
}

export default App;