import { Container } from '@mui/system';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import SearchBar from '../../SearchBar';
import TitlePage from '../TitlePage';

const HistoryOrder = () => {
  const [listOrder, setListOrder] = useState([]);
  useEffect(() => {
    var config = {
      method: 'get',
      url: `http://localhost:8989/api/client/get-list-history?user-id=${localStorage.getItem(
        'userID'
      )}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        setListOrder(response.data.data);
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
        <Table striped hover>
          <thead>
            <tr>
              <th>Mã</th>
              <th className="center">Tên sảnh</th>
              <th className="center">Ngày tạo</th>
              <th className="center">Ngày tổ chức</th>
              <th className="center">Buổi</th>
              <th className="center">Tổng tiền</th>
              <th className="center"></th>
            </tr>
          </thead>
          <tbody>
            {listOrder.map((order) => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td className="center">{order.lobbyName}</td>
                <td className="center">{order.createDate}</td>
                <td className="center">{order.bookingDate}</td>
                <td className="center">{order.lesson}</td>
                <td className="center">
                  <i>
                    <FormatPrice price={order.totalMoney} /> VND
                  </i>
                </td>
                {order.payment ? (
                  <td className="center">Đã thanh toán</td>
                ) : (
                  <td className="center">
                    <Button variant="success">Thanh Toán Momo</Button>
                  </td>
                )}
              </tr>
            ))}
          </tbody>
        </Table>
      </Container>
      <br></br>
    </>
  );
};

export default HistoryOrder;
