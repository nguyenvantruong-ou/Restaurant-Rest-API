import React from 'react';

import { Link, useLocation } from 'react-router-dom';

import './Sidebar.css';
import sidebar_items from './SidebarData';
import SidebarItem from './SidebarItem';
import LogoutIcon from '@mui/icons-material/Logout';

const Sidebar = () => {
  const slug = useLocation();
  const activeItem = sidebar_items.findIndex(
    (item) => item.path === slug.pathname
  );
  return (
    <div className="sidebar">
      <div className="sidebar__logo">
        <img
          src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1651647841/q4lhebqkmpf4qj7rzhuo.png"
          alt="company logo"
        />
      </div>
      {sidebar_items.map((item, index) => (
        <Link to={item.path} key={index}>
          <SidebarItem
            title={item.name}
            icon={item.icon}
            active={index === activeItem}
          />
        </Link>
      ))}
    </div>
  );
};

export default Sidebar;
