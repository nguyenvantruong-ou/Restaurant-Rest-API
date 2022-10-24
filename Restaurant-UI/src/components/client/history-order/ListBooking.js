import { Container } from '@mui/system';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import SearchBar from '../../SearchBar';
import TitlePage from '../TitlePage';

const ListBooking = () => {
  const [listBooking, setListBooking] = useState([]);
  useEffect(() => {
    var config = {
      method: 'get',
      url: 'http://localhost:8989/api/client/staff-get-all-order?phone-number=',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        setListBooking(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <>
      <br></br>
      <TitlePage title="Danh sách hoá đơn chưa thanh toán" />
      <Container>
        <SearchBar />
        <Table striped hover>
          <thead>
            <tr>
              <th>Mã</th>
              <th className="center">Tên</th>
              <th className="center">Số điện thoại</th>
              <th className="center">Ngày tổ chức</th>
              <th className="center">Buổi</th>
              <th className="center">Tổng tiền</th>
              <th className="center"></th>
            </tr>
          </thead>
          <tbody>
            {listBooking.map((booking) => (
              <tr key={booking.id}>
                <td>{booking.id}</td>
                <td className="center">{booking.name}</td>
                <td className="center">{booking.phoneNumber}</td>
                <td className="center">{booking.bookingDate}</td>
                <td className="center">{booking.lesson}</td>
                <td className="center">
                  <i>
                    <FormatPrice price={booking.totalMoney} /> VND
                  </i>
                </td>{' '}
                <td className="center">
                  <Button variant="success">Thanh Toán</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default ListBooking;
