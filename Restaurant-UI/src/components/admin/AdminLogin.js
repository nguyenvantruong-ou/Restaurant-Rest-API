import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import { Link, useNavigate } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from 'axios';
import { toast } from 'react-toastify';
import { Paper } from '@mui/material';

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {'Copyright © '}
      <Link to={'/'}>ThanhVan website</Link> {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

export default function Login() {
  const nav = useNavigate();
  localStorage.clear();
  document.title = 'Đăng nhập quản trị viên';
  document.querySelector("link[rel*='icon']").href =
    'https://res.cloudinary.com/dqifjhxxg/image/upload/v1663947929/restaurant%20management/1791961_tlcqcp.png';
  const handleSubmit = (event) => {
    event.preventDefault();

    const info = new FormData(event.currentTarget);
    let data = JSON.stringify({
      username: info.get('username'),
      password: info.get('password'),
    });

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
        localStorage.setItem('accessToken', res.data.token);
        localStorage.setItem('userID', res.data.userID);
        localStorage.setItem('username', res.data.username);
        localStorage.setItem('role', res.data.roles);
        if (res.code === 200) {
          toast.success('đăng nhập thành công');
          document.title = 'Quản trị hệ thống';
          document.querySelector("link[rel*='icon']").href =
            'https://res.cloudinary.com/dqifjhxxg/image/upload/v1651647841/q4lhebqkmpf4qj7rzhuo.png';
          nav('/admin');
        }
      })
      .catch(function (error) {
        toast.error('đăng nhập thất bại');
      });
  };

  return (
    <div
      className="bg-login"
      style={{
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundImage: 'linear-gradient(to bottom right, #0b5f5f, #000000)',
      }}
    >
      <Container component="main" maxWidth="xs">
        <Paper
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '30px',
          }}
          elevation={10}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Đăng nhập quản trị viên
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              label="Tên đăng nhập"
              name="username"
              autoComplete="username"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Mật khẩu"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Nhớ tài khoản"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Đăng nhập
            </Button>
          </Box>{' '}
          <Copyright sx={{ mt: 8, mb: 4 }} />
        </Paper>
      </Container>
    </div>
  );
}
