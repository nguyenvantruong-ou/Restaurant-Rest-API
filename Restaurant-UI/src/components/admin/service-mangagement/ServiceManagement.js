import React from 'react';
import Title from '../Title';
import SearchBar from '../../SearchBar';
import TableService from './TableService';
import { useState } from 'react';
import { useEffect } from 'react';
import axios from 'axios';
import Error403 from '../../ErrorPages/Error403';

const ServiceManagement = () => {
  const [serviceData, setServiceData] = useState([]);
  const role = localStorage.getItem('role');
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/admin/get-list-service?kw=',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setServiceData(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Quản lý dịch vụ'} />
        <SearchBar />
        <TableService serviceList={serviceData} setService={setServiceData} />
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

export default ServiceManagement;
