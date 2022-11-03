import React from 'react';

import { Outlet } from 'react-router-dom';

import Header from './header/Header';
import Footer from './footer/Footer';
import './client.css';

const ClientLayout = () => {
  document.title = 'Thành Văn';
  document.querySelector("link[rel*='icon']").href =
    'https://res.cloudinary.com/dqifjhxxg/image/upload/v1667017299/restaurant%20management/cheers_updtj6.png';
  return (
    <>
      <Header />
      <div style={{ marginTop: '56px' }}>
        <div className="main">
          <Outlet />
        </div>
      </div>
      <Footer />
    </>
  );
};

export default ClientLayout;
