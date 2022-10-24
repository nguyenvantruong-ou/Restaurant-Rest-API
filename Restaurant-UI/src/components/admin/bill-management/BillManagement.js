import React, { useEffect, useState } from 'react';
import Title from '../Title';
import SearchBar from '../../SearchBar';
import TableLobby from './TableLobby';
import axios from 'axios';

const BillManagement = () => {
  const [lobbyData, setLobbyData] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/admin/get-list-lobby?size=4&page=1&kw=',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setLobbyData(res.data.listLobby);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <>
      <Title title={'Quản lý sảnh cưới'} />
      <SearchBar />
      <TableLobby listLobby={lobbyData} />
    </>
  );
};

export default BillManagement;
