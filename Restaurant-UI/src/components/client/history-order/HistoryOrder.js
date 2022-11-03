import { Container } from '@mui/system';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import Error403 from '../../ErrorPages/Error403';
import FormatPrice from '../../FormatPrice';
import TitlePage from '../TitlePage';

const HistoryOrder = () => {
  const role = localStorage.getItem('role');
  const [listOrder, setListOrder] = useState([]);

  const handleMomoPayment = (ordID) => {
    var data = JSON.stringify({
      userID: localStorage.getItem('userID'),
      orderID: ordID,
    });

    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/client/payment-momo',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json',
      },
      data: data,
    };

    axios(config)
      .then(function (response) {
        //window.open(response.data.data);
        window.location.href = response.data.data;
      })
      .catch(function (error) {
        console.log(error);
      });
  };
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
  if (role === 'USER') {
    return (
      <>
        <br></br>
        <TitlePage title="Danh sách tiệc đã đặt" />
        <Container>
          <Table striped hover style={{ border: '5px solid #cfa92b' }}>
            <thead>
              <tr style={{ backgroundColor: '#cfa92b', color: '#790000' }}>
                <th>Mã</th>
                <th className="center">Tên sảnh</th>
                <th className="center">Ngày tạo</th>
                <th className="center">Ngày tổ chức</th>
                <th className="center">Buổi</th>
                <th className="center">Tổng tiền</th>
                <th className="center"></th>
              </tr>
            </thead>
            {listOrder !== null ? (
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
                      <td className="center">
                        <i style={{ fontWeight: '500', color: 'darkred' }}>
                          Đã thanh toán
                        </i>
                      </td>
                    ) : (
                      <td className="btn-momo center">
                        <Button onClick={() => handleMomoPayment(order.id)}>
                          Thanh Toán Momo
                        </Button>
                      </td>
                    )}
                  </tr>
                ))}
              </tbody>
            ) : (
              ''
            )}
          </Table>
        </Container>
        <br></br>
      </>
    );
  } else {
    return <Error403 links={'/auth/login'} />;
  }
};

export default HistoryOrder;
