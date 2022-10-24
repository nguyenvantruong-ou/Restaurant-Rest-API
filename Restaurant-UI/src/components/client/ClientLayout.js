import React from 'react';

import { Outlet } from 'react-router-dom';

import Header from './header/Header';
import Footer from './footer/Footer';
// import ThumbnailSlider from './thumbnail-slider/ThumbnailSlider';

const ClientLayout = () => {
  document.title = 'Thành Văn';
  document.querySelector("link[rel*='icon']").href =
    '../../assets/images/logo.png';
  return (
    <>
      <Header />
      <div style={{ marginTop: '56px' }}>
        <div className="main">
          <Outlet />
        </div>
      </div>
      {/* <ThumbnailSlider /> */}
      <Footer />
    </>
  );
};

export default ClientLayout;
