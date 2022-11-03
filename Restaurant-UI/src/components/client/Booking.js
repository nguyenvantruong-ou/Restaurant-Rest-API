import React, { useEffect, useState } from 'react';
import { Container } from '@mui/system';
import TitlePage from './TitlePage';
import Error403 from '../ErrorPages/Error403';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { Col, Modal, Row, Table } from 'react-bootstrap';
import { toast } from 'react-toastify';
import axios from 'axios';
import FormatPrice from '../FormatPrice';
import FormatDate from '../FormatDate';
import { useNavigate } from 'react-router';

const Booking = () => {
  const nav = useNavigate();
  const [showModal, setShowModal] = useState(false);
  const [menu, setMenuOrder] = useState([]);
  const [menuList, setMenuList] = useState([]);
  const [services, setServicesOrder] = useState([]);
  const [servicesList, setServicesList] = useState([]);
  const [numberTable, setNumberTable] = useState(0);
  const [bookingDate, setBookingDate] = useState(
    FormatDate.formatFullDate(new Date())
  );
  const [addItem, setAddItem] = useState(false);
  const [lobbies, setLobbies] = useState([]);
  const role = localStorage.getItem('role');

  const handleModalClose = () => {
    setShowModal(false);
    nav('/');
  };
  const paymentConfirm = () => {
    nav('/history-order');
  };

  const handleAddMenu = () => {
    if (numberTable !== 0) {
      const menuID = document.getElementById('menu').value;
      const idx = menu.findIndex((item) => item.id.toString() === menuID);
      const index = menuList.findIndex((item) => item.id.toString() === menuID);
      if (idx > -1) {
        menu[idx].totalTable += numberTable;
        setAddItem(true);
      } else {
        menuList[index].totalTable = numberTable;
        setMenuOrder((prev) => [...prev, menuList[index]]);
      }
    } else {
      toast.error('Vui lòng chọn số bàn ở mục menu');
    }
  };

  const handleAddService = () => {
    const serID = document.getElementById('service').value;
    const idx = services.findIndex((item) => item.id.toString() === serID);
    const index = servicesList.findIndex(
      (item) => item.id.toString() === serID
    );
    if (idx < 0) {
      setServicesOrder((prev) => [...prev, servicesList[index]]);
      setAddItem(true);
    } else {
      toast.error('Bạn đã chọn dịch vụ này');
    }
  };

  const handleOrderSubmit = (event) => {
    event.preventDefault();
    if (bookingDate > FormatDate.formatFullDate(new Date()))
      if (menu.length > 0) {
        const lesson = document.getElementById('lesson').value;
        const lobID = document.getElementById('lobby').value;
        const listServices = services.map((item) => {
          return { serID: item.id };
        });
        const listMenu = menu.map((item) => {
          return { menuID: item.id, amountTable: item.totalTable };
        });
        let data = JSON.stringify({
          ord_booking_date: bookingDate,
          ord_booking_lesson: lesson,
          user_id: localStorage.getItem('userID'),
          lob_id: lobID,
          listService: listServices,
          listMenu: listMenu,
        });
        let config = {
          method: 'post',
          url: 'http://localhost:8989/api/client/order-lobby',
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
            'Content-Type': 'application/json',
          },
          data: data,
        };
        axios(config)
          .then(function (response) {
            if (response.data.code === 200) {
              toast.success(response.data.message);
              setShowModal(true);
            } else {
              toast.info(response.data.message);
            }
          })
          .catch(function (error) {
            toast.error(error.message);
          });
      } else {
        toast.error('Vui lòng chọn thực đơn kèm theo');
      }
    else {
      toast.error('Ngày đặt tiệc không hợp lệ');
    }
  };
  const handleNumberTable = (event) => {
    setNumberTable(parseInt(event.target.value));
  };
  const handleBookingDate = (event) => {
    setBookingDate(event.target.value);
  };

  // const handleNumberTableChange = (event, { id }) => {
  //   const idx = menu.findIndex((item) => item.id === id);
  //   console.log(idx);
  //   console.log(id);
  //   menu[idx].totalTable = event.target.value;
  //   console.log(menu[idx].totalTable);
  // };

  // xoá order item
  const handleDeleteService = (id) => {
    const idx = services.findIndex((item) => item.id === id);
    if (idx > -1) {
      const arrCopy = [...services];
      arrCopy.splice(idx, 1);
      setServicesOrder(arrCopy);
      toast.success('xoá thành công');
    } else {
      toast.error('đã có lỗi xảy ra');
    }
  };
  const handleDeleteMenu = (id) => {
    const idx = menu.findIndex((item) => item.id === id);
    if (idx > -1) {
      const arrCopy = [...menu];
      arrCopy.splice(idx, 1);
      setMenuOrder(arrCopy);
      toast.success('xoá thành công');
    } else {
      toast.error('đã có lỗi xảy ra');
    }
  };

  useEffect(() => {
    //get lobby list
    let configLobby = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-lobby-combobox',
    };
    axios(configLobby)
      .then(function (response) {
        const res = response.data;
        setLobbies(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    //get service list
    let configService = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-service',
    };
    axios(configService)
      .then(function (response) {
        const res = response.data;
        setServicesList(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
    //get menu list
    let configMenu = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-menu-dish?sort=default',
    };
    axios(configMenu)
      .then(function (response) {
        const res = response.data;
        setMenuList(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
    // set lại state để render ui
    setAddItem(false);
  }, [addItem, services]);
  if (role === 'USER') {
    return (
      <Container>
        <div className="border">
          <div style={{ marginTop: 10 }}>
            <TitlePage title="Đặt tiệc" />
          </div>
          <Form style={{ padding: '0 40px' }} onSubmit={handleOrderSubmit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Row>
                <Col>
                  <Form.Label>Ngày tổ chức</Form.Label>
                  <Form.Control
                    onChange={handleBookingDate}
                    defaultValue={bookingDate}
                    name="booking-date"
                    type="date"
                  />
                </Col>
                <Col>
                  <Form.Label>Buổi</Form.Label>
                  <Form.Select
                    name="lesson"
                    id="lesson"
                    aria-label="Default select example"
                  >
                    <option value="Sáng">🌞 Sáng</option>
                    <option value="Chiều">🌤️ Chiều</option>
                    <option value="Tối">🌃 Tối</option>
                  </Form.Select>
                </Col>
                <Col>
                  <Form.Label>Sảnh</Form.Label>
                  <Form.Select
                    id="lobby"
                    name="lobby"
                    aria-label="Default select example"
                  >
                    {lobbies.map((lobby) => (
                      <option key={lobby.lobId} value={lobby.lobId}>
                        {lobby.lobName}
                      </option>
                    ))}
                  </Form.Select>
                </Col>
              </Row>
            </Form.Group>
            <Form.Group>
              <Row>
                <Col sm={4}>
                  <Form.Label>Thực đơn</Form.Label>
                  <Form.Select
                    name="menu"
                    id="menu"
                    aria-label="Default select example"
                  >
                    {menuList.map((menu) => (
                      <option key={menu.id} value={menu.id}>
                        {menu.menuName}
                      </option>
                    ))}
                  </Form.Select>
                  <Form.Label style={{ marginTop: '10px' }}>Số bàn</Form.Label>
                  <Form.Control
                    onChange={handleNumberTable}
                    name="number-table"
                    type="number"
                    min={0}
                  />
                  <Button
                    style={{
                      marginTop: '10px',
                      border: 'none',
                      backgroundColor: '#7F1734',
                    }}
                    onClick={handleAddMenu}
                    variant="primary"
                  >
                    Thêm thực đơn
                  </Button>
                </Col>
                <Col>
                  <Table>
                    <thead style={{ backgroundColor: '#dfca83' }}>
                      <tr>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Giá</th>
                        <th>Số bàn</th>
                        <th>Tổng phí</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      {menu.map((item) => {
                        return (
                          <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.menuName}</td>
                            <td>
                              <FormatPrice price={item.menuPrice} />
                            </td>
                            <td>{item.totalTable}</td>
                            <td>
                              <FormatPrice
                                price={item.totalTable * item.menuPrice}
                              />
                            </td>
                            <td>
                              <Button
                                onClick={() => handleDeleteMenu(item.id)}
                                variant="danger"
                              >
                                Xoá
                              </Button>
                            </td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                </Col>
              </Row>
            </Form.Group>
            <Form.Group style={{ marginTop: '10px' }}>
              <Row>
                <Col sm={4}>
                  <Form.Label>Dịch vụ</Form.Label>
                  <Form.Select
                    name="service"
                    id="service"
                    aria-label="Default select example"
                  >
                    {servicesList.map((item) => (
                      <option key={item.id} value={item.id}>
                        {item.serName}
                      </option>
                    ))}
                  </Form.Select>
                  <Button
                    style={{
                      marginTop: '10px',
                      border: 'none',
                      backgroundColor: '#7F1734',
                    }}
                    onClick={handleAddService}
                    variant="primary"
                  >
                    Thêm dịch vụ
                  </Button>
                </Col>
                <Col>
                  <Table striped hover>
                    <thead style={{ backgroundColor: '#dfca83' }}>
                      <tr>
                        <th>Mã</th>
                        <th>Tên dịch vụ</th>
                        <th>Giá</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      {services.map((service) => {
                        return (
                          <tr key={service.id}>
                            <td>{service.id}</td>
                            <td>{service.serName}</td>
                            <td>
                              <FormatPrice price={service.serPrice} />
                            </td>
                            <td>
                              <Button
                                variant="danger"
                                onClick={() => handleDeleteService(service.id)}
                              >
                                Xoá
                              </Button>
                            </td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                </Col>
              </Row>
            </Form.Group>
            <Form.Group style={{ textAlign: 'center', margin: '20px' }}>
              <Button
                style={{
                  padding: '10px 50px',
                  border: 'none',
                  backgroundColor: '#7F1734',
                }}
                type="submit"
              >
                Đặt tiệc
              </Button>
            </Form.Group>
          </Form>
        </div>
        <Modal centered show={showModal} onHide={handleModalClose}>
          <Modal.Header closeButton>
            <Modal.Title>Quý khách đã đặt thành công</Modal.Title>
          </Modal.Header>
          <Modal.Body>Quý khách có muốn thanh toán ngay?</Modal.Body>
          <Modal.Footer>
            <Button variant="success" onClick={handleModalClose}>
              Thanh toán sau
            </Button>
            <Button variant="danger" onClick={paymentConfirm}>
              Thanh Toán
            </Button>
          </Modal.Footer>
        </Modal>
      </Container>
    );
  } else return <Error403 />;
};

export default Booking;
