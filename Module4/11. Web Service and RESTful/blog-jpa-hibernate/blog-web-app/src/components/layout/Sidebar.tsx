
import { Link } from 'react-router-dom';

const Sidebar = () => {
  return (
    <aside className="w-64 bg-secondary text-secondary-foreground p-4">
      <h2 className="text-lg font-semibold mb-4">Menu</h2>
      <nav>
        <ul>
          <li className="mb-2">
            <Link to="/" className="hover:underline">Home</Link>
          </li>
          <li className="mb-2">
            <Link to="/create" className="hover:underline">Create Blog Post</Link>
          </li>
          <li className="mb-2">
            <Link to="/categories" className="hover:underline">Category Management</Link>
          </li>
        </ul>
      </nav>
    </aside>
  );
};

export default Sidebar;
