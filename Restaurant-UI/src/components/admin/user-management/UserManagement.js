import React, { useEffect, useState } from 'react';
import Title from '../Title';
import TableUser from './TableUser';
import axios from 'axios';
import Error403 from '../../ErrorPages/Error403';

const UserManagement = () => {
  const role = localStorage.getItem('role');
  const [userData, setUserData] = useState([]);
  const [keyWord, setKeyWord] = useState('');
  const handleSearch = () => {
    setKeyWord(document.getElementById('search').value.trim());
  };
  useEffect(() => {
    let config2 = {
      method: 'get',
      url: `http://localhost:8989/api/admin/get-user-by-name?size=8&page=1&kw=${keyWord}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    axios(config2)
      .then(function (response) {
        const res = response.data;
        setUserData(res.data.listUser);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [keyWord]);
  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Quản lý người dùng'} />
        <div>
          <input
            id="search"
            className="search search-admin"
            placeholder="Nhập tên nhân viên..."
            onChange={handleSearch}
          ></input>
        </div>
        <TableUser userData={userData} />
      </>
    );
  } else {
    return (
      <>
        <Error403 links={'/admin/login'} />
      </>
    );
  }
};

export default UserManagement;
