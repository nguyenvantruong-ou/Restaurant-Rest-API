import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from 'axios';
import { Button, Col, Form, Modal, Row } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { Paper } from '@mui/material';

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      fontSize={16}
      color="black"
      align="right"
      {...props}
    >
      {'Copyright © '}
      <Link to="/" style={{ textDecoration: 'none' }}>
        ThanhVan website
      </Link>{' '}
      {new Date().getFullYear()}
    </Typography>
  );
}

export default function SignUp() {
  const nav = useNavigate();
  const [validated, setValidated] = useState(false);
  document.title = 'Đăng kí';
  document.querySelector("link[rel*='icon']").href =
    'https://res.cloudinary.com/dqifjhxxg/image/upload/v1663947929/restaurant%20management/1791961_tlcqcp.png';
  const [account, setAccount] = useState();
  const [showConfirm, setShowConfirm] = useState(false);
  const [mail, setMail] = useState('');
  const handleConfirmClose = () => setShowConfirm(false);
  const confirm = () => {
    const code = document.getElementById('code').value;
    let config = {
      method: 'post',
      url: `http://localhost:8989/api/client/confirm-email?code=${code}`,
    };

    axios(config)
      .then(function (response) {
        if (response.data.code === 200) {
          toast.success(response.data.message, {
            position: toast.POSITION.BOTTOM_RIGHT,
          });
          nav('/auth/login');
          const data = {
            username: account.username,
            password: account.password,
          };
          let config = {
            method: 'post',
            url: 'http://localhost:8989/api/login',
            headers: {
              'Content-Type': 'application/json',
            },
            data: data,
          };
          axios(config)
            .then(function (response) {
              var res = response.data;
              if (res.code === 200) {
                nav('/');
                localStorage.setItem('accessToken', res.data.token);
                localStorage.setItem('userID', res.data.userID);
                localStorage.setItem('username', res.data.username);
                localStorage.setItem('role', res.data.roles);
                toast.success(response.data.message, {
                  position: toast.POSITION.BOTTOM_RIGHT,
                });
              }
            })
            .catch(function (error) {
              toast.error('Đăng nhập thất bại');
            });
        } else {
          toast.error(response.data.message);
        }
      })
      .catch(function (error) {
        toast.error(error.message);
      });
  };

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    event.preventDefault();
    if (form.checkValidity() === true) {
      const data = new FormData(event.currentTarget);
      const infoAccount = JSON.stringify({
        userIdCard: data.get('idCard'),
        userPhoneNumber: data.get('phone'),
        userSex: data.get('gender') ? true : false,
        userLastName: data.get('lastName'),
        userFirstName: data.get('firstName'),
        userDateOfBirth: '2001-12-12',
        userUsename: data.get('username'),
        userPassword: data.get('password'),
        userEmail: data.get('email'),
        userAddress: data.get('address'),
      });
      setMail(data.get('email'));
      setAccount({
        username: data.get('username'),
        password: data.get('password'),
      });
      data.append('user', infoAccount);

      var config = {
        method: 'post',
        url: 'http://localhost:8989/api/client/register',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: data,
      };
      toast.info('chúng tôi sẽ gửi mail xác nhận cho bạn trong giây lát');
      axios(config)
        .then(function (response) {
          if (response.data.code === 200) {
            toast.info(response.data.message);
            setShowConfirm(true);
          } else {
            toast.info('Thông tin sai ' + response.data.message);
          }
        })
        .catch(function (error) {
          toast.info(error.message);
        });
    }
    setValidated(true);
  };
  return (
    <div
      style={{
        backgroundColor: 'lightblue',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        backgroundImage:
          'linear-gradient(to bottom right, rgb(29, 28, 229), rgb(196, 122, 255))',
      }}
    >
      <Container component="main" maxWidth="md">
        <Paper
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '10px 0',
          }}
          elevation={10}
        >
          <Typography
            sx={{
              margin: '10px',
              color: '#4533ea',
              textTransform: 'uppercase',
              fontWeight: 'bolder',
            }}
            component="h1"
            variant="h5"
          >
            tạo tài khoản
          </Typography>
          <Form
            noValidate
            validated={validated}
            style={{ width: '80%' }}
            onSubmit={handleSubmit}
          >
            <Form.Group className="mb-2">
              <Row>
                <Col sm={7}>
                  <Form.Label>Họ & đệm</Form.Label>
                  <Form.Control
                    controlid="validationCustom01"
                    required
                    type="text"
                    name="lastName"
                  />
                </Col>
                <Col>
                  <Form.Label>Tên</Form.Label>
                  <Form.Control
                    controlid="validationCustom02"
                    required
                    name="firstName"
                    type="text"
                  />
                </Col>
              </Row>
            </Form.Group>
            <Form.Group className="mb-2">
              <Form.Label>Email</Form.Label>
              <Form.Control
                controlid="validationCustom010"
                required
                type="email"
                name="email"
              />
            </Form.Group>
            <Form.Group className="mb-2">
              <Row>
                <Col>
                  <Form.Label>CCCD/CMND</Form.Label>
                  <Form.Control
                    controlid="validationCustom03"
                    required
                    type="text"
                    name="idCard"
                  />
                </Col>
                <Col>
                  <Form.Label>Số điện thoại</Form.Label>
                  <Form.Control
                    controlid="validationCustom033"
                    required
                    name="phone"
                    type="text"
                  />
                </Col>
              </Row>
            </Form.Group>
            <Form.Group className="mb-2">
              <Form.Label>Địa chỉ</Form.Label>
              <Form.Control
                controlid="validationCustom04"
                required
                name="address"
                type="text"
              />
            </Form.Group>
            <Form.Group className="mb-2">
              <Row>
                <Col>
                  <Form.Label>Tài khoản</Form.Label>
                  <Form.Control
                    controlid="validationCustom05"
                    required
                    type="text"
                    name="username"
                  />
                </Col>
                <Col>
                  <Form.Label>Mật khẩu</Form.Label>
                  <Form.Control
                    controlid="validationCustom054"
                    required
                    name="password"
                    type="password"
                  />
                </Col>
              </Row>
            </Form.Group>
            <Form.Group className="mb-3">
              <Row>
                <Col sm={4}>
                  <Form.Label>Giới tính</Form.Label>
                  <Form.Select name="gender">
                    <option value={0}>Nữ</option>
                    <option value={1}>Nam</option>
                  </Form.Select>
                </Col>
                <Col>
                  <Form.Label>Ảnh đại diện</Form.Label>
                  <Form.Control
                    controlid="validationCustom06"
                    required
                    id="file"
                    name="file"
                    type="file"
                  />
                  <Form.Control.Feedback type="invalid">
                    Please choose a file.
                  </Form.Control.Feedback>
                </Col>
              </Row>
            </Form.Group>
            <Form.Group
              style={{
                marginTop: '20px',
                textAlign: 'center',
              }}
            >
              <Button
                style={{ padding: '10px 100px' }}
                variant="primary"
                type="submit"
              >
                Đăng kí
              </Button>
            </Form.Group>
            <Form.Group>
              <Row>
                <Col sm={5}>
                  <div style={{ margin: '20px 0 0 0' }}>
                    <Link to={'/auth/login'} variant="body2">
                      {'Đã có tài khoản? Đăng nhập'}
                    </Link>
                  </div>
                </Col>
                <Col sm={7}>
                  <Copyright sx={{ mt: 2 }} />
                </Col>
              </Row>
            </Form.Group>
          </Form>
        </Paper>
        <Modal centered show={showConfirm} onHide={handleConfirmClose}>
          <Modal.Header closeButton>
            <Modal.Title>Xác thực tài khoản</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div>Vui lòng nhập mã xác thực đã được gửi qua mail: {mail}</div>
            <input id="code" style={{ textAlign: 'center' }} maxLength={6} />
          </Modal.Body>
          <Modal.Footer>
            <Button variant="success" onClick={handleConfirmClose}>
              Huỷ
            </Button>
            <Button variant="danger" onClick={confirm}>
              Xác Nhận
            </Button>
          </Modal.Footer>
        </Modal>
      </Container>
    </div>
  );
}
