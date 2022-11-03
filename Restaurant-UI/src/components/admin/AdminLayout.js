import React from 'react';
import Sidebar from './sidebar/Sidebar';
import { Outlet } from 'react-router-dom';
import './Admin.css';

const AdminLayout = () => {
  document.title = 'Quản trị hệ thống';
  document.querySelector("link[rel*='icon']").href =
    'https://res.cloudinary.com/dqifjhxxg/image/upload/v1651647841/q4lhebqkmpf4qj7rzhuo.png';
  return (
    <>
      <Sidebar />
      <div className="content">
        <Outlet />
      </div>
    </>
  );
};

export default AdminLayout;
