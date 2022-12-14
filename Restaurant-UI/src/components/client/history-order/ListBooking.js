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
        <TitlePage title="Danh s??ch ti???c ch??a thanh to??n" />
        <Container>
          <div>
            <input
              id="phone-search"
              className="search"
              placeholder="Nh???p s??? ??i???n tho???i..."
              onChange={handlePhoneSearch}
            ></input>
          </div>
          <Table striped hover style={{ border: '5px solid #cfa92b' }}>
            <thead>
              <tr style={{ backgroundColor: '#cfa92b', color: '#790000' }}>
                <th>M??</th>
                <th className="center">T??n</th>
                <th className="center">S??? ??i???n tho???i</th>
                <th className="center">Ng??y t??? ch???c</th>
                <th className="center">Bu???i</th>
                <th className="center">T???ng ti???n</th>
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
                        X??c nh???n Thanh To??n
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
              <Modal.Title>X??c nh???n ????n h??ng n??y?</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div className="info-order">
                <div className="center">
                  M?? ti???c: <b>{order.id}</b>
                </div>
                <div className="center">
                  H??? & t??n kh??ch h??ng: <b>{order.name}</b>
                </div>
                <div className="center">
                  S??? ??i???n tho???i: <b>{order.phoneNumber}</b>
                </div>
                <div className="center">
                  Ng??y t??? ch???c: <b>{order.bookingDate}</b>
                </div>
                <div className="center">
                  Bu???i t??? ch???c: <b>{order.lesson}</b>
                </div>
                <div className="center">
                  T???ng chi ph??:
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
                Hu???
              </Button>
              <Button variant="danger" onClick={DeleteConfirm}>
                X??c Nh???n
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
