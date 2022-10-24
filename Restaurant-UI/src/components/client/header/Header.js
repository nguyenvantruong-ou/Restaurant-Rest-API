import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Navbar, Container, Nav } from 'react-bootstrap';

const Header = () => {
  const nav = useNavigate();
  const [isLogin, setIsLogin] = useState(
    localStorage.getItem('accessToken') != null
  );

  const role = localStorage.getItem('role');

  const logoutClick = () => {
    setIsLogin(false);
    localStorage.removeItem('accessToken');
    localStorage.removeItem('username');
    localStorage.removeItem('userID');
    localStorage.removeItem('role');
    nav('/');
  };

  return (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" fixed="top">
      <Container>
        <Navbar.Brand as={Link} to="/">
          <img
            alt=""
            src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1650674202/restaurant%20management/toast_d2vk24.png"
            width="30"
            height="30"
            className="d-inline-block align-top"
          />{' '}
          Thành Văn
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/lobby">
              Sảnh tiệc
            </Nav.Link>
            <Nav.Link as={Link} to="/service">
              Dịch vụ
            </Nav.Link>
            <Nav.Link as={Link} to="/menu">
              Thực đơn
            </Nav.Link>
            <Nav.Link as={Link} to="/contact">
              Liên hệ
            </Nav.Link>
            {role === 'USER' ? (
              <Nav.Link as={Link} to="/booking">
                Đặt tiệc
              </Nav.Link>
            ) : (
              ''
            )}
          </Nav>
          {isLogin ? (
            <Nav>
              {role === 'USER' ? (
                <Nav.Link as={Link} to="/history-order">
                  Lịch sử đặt tiệc
                </Nav.Link>
              ) : (
                ''
              )}
              {role === 'STAFF' ? (
                <Nav.Link as={Link} to="/list-booking">
                  Danh sách hoá đơn
                </Nav.Link>
              ) : (
                ''
              )}
              <Nav.Link style={{ color: '#d3ac2b' }}>
                {localStorage.getItem('username')}
              </Nav.Link>
              <Nav.Link onClick={logoutClick}>Đăng xuất</Nav.Link>
            </Nav>
          ) : (
            <Nav>
              <Nav.Link as={Link} to="/auth/register">
                Đăng ký
              </Nav.Link>
              <Nav.Link as={Link} to="/auth/login">
                Đăng nhập
              </Nav.Link>
            </Nav>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
