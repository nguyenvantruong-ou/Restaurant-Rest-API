import React, { useEffect, useState } from 'react';
import Title from '../Title';
import SearchBar from '../../SearchBar';
import TableBill from './TableBill';
import axios from 'axios';
import Error403 from '../../ErrorPages/Error403';

const BillManagement = () => {
  const role = localStorage.getItem('role');
  const [bills, setBills] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/admin/get-list-bill',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setBills(res.data);
        console.log(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Danh sách tất cả hoá đơn'} />
        <SearchBar />
        <TableBill listBills={bills} />
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

export default BillManagement;
