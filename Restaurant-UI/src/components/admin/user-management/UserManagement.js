import React, { useEffect, useState } from 'react';
import Title from '../Title';
import SearchBar from '../../SearchBar';
import TableUser from './TableUser';
import axios from 'axios';
import Error403 from '../../ErrorPages/Error403';

const UserManagement = () => {
  const role = localStorage.getItem('role');
  const [userData, setUserData] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/admin/get-user?size=8&page=1&type=all',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setUserData(res.data.listUser);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Quản lý người dùng'} />
        <SearchBar />
        <TableUser userData={userData} />
      </>
    );
  } else {
    return (
      <>
        <Error403 />
      </>
    );
  }
};

export default UserManagement;
