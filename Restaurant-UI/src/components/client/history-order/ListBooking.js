import { colors } from '@mui/material';
import { Container } from '@mui/system';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';
import { toast } from 'react-toastify';
import Error403 from '../../ErrorPages/Error403';
import FormatPrice from '../../FormatPrice';
import TitlePage from '../TitlePage';
import './list-order.css';

const ListBooking = () => {
  const role = localStorage.getItem('role');
  const [listBooking, setListBooking] = useState([]);
  const [phone, setPhone] = useState('');
  const handlePhoneSearch = () => {
    setPhone(document.getElementById('phone-search').value);
  };
  useEffect(() => {
    var config = {
      method: 'get',
      url: `http://localhost:8989/api/client/staff-get-all-order?phone-number=${phone}`,
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
  }, [phone]);
  const [showDelete, setDeleteShow] = useState(false);
  const [order, setOrder] = useState({});
  const handleDeleteClose = () => setDeleteShow(false);
  const handleDeleteShow = (order) => {
    setOrder(order);
    setDeleteShow(true);
  };
  const DeleteConfirm = () => {
    var config = {
      method: 'post',
      url: `http://localhost:8989/api/client/staff-confirm-user-payment`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
      data: {
        userID: localStorage.getItem('userID'),
        orderID: order.id,
      },
    };
    axios(config)
      .then(function (response) {
        toast.success(response.data.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
        setDeleteShow(false);
        const idx = listBooking.findIndex((value) => value.id === order.id);
        const arrCopy = [...listBooking];
        arrCopy.splice(idx, 1);
        setListBooking(arrCopy);
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };

  if (role === 'STAFF') {
    return (
      <>
        <br></br>
        <TitlePage title="Danh sách tiệc chưa thanh toán" />
        <Container>
          <div>
            <input
              id="phone-search"
              className="search"
              placeholder="Nhập số điện thoại..."
              onChange={handlePhoneSearch}
            ></input>
          </div>
          <Table striped hover style={{ border: '5px solid #cfa92b' }}>
            <thead>
              <tr style={{ backgroundColor: '#cfa92b', color: '#790000' }}>
                <th>Mã</th>
                <th className="center">Tên</th>
                <th className="center">Số điện thoại</th>
                <th className="center">Ngày tổ chức</th>
                <th className="center">Buổi</th>
                <th className="center">Tổng tiền</th>
                <th className="center"></th>
              </tr>
            </thead>
            {listBooking != null ? (
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
                    </td>
                    <td className="center">
                      <Button
                        onClick={() => handleDeleteShow(booking)}
                        variant="outline-success"
                      >
                        Xác nhận Thanh Toán
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            ) : (
              ''
            )}
          </Table>
          <Modal show={showDelete} onHide={handleDeleteClose}>
            <Modal.Header closeButton>
              <Modal.Title>Xác nhận đơn hàng này?</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div className="info-order">
                <div className="center">
                  Mã tiệc: <b>{order.id}</b>
                </div>
                <div className="center">
                  Họ & tên khách hàng: <b>{order.name}</b>
                </div>
                <div className="center">
                  Số điện thoại: <b>{order.phoneNumber}</b>
                </div>
                <div className="center">
                  Ngày tổ chức: <b>{order.bookingDate}</b>
                </div>
                <div className="center">
                  Buổi tổ chức: <b>{order.lesson}</b>
                </div>
                <div className="center">
                  Tổng chi phí:
                  <i>
                    <b>
                      <FormatPrice price={order.totalMoney} /> VND
                    </b>
                  </i>
                </div>
              </div>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="success" onClick={handleDeleteClose}>
                Huỷ
              </Button>
              <Button variant="danger" onClick={DeleteConfirm}>
                Xác Nhận
              </Button>
            </Modal.Footer>
          </Modal>
        </Container>
      </>
    );
  } else {
    return <Error403 links={'/auth/login'} />;
  }
};

export default ListBooking;
