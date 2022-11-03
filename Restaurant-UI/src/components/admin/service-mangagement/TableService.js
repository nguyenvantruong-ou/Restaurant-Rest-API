import axios from 'axios';
import React from 'react';
import { useState } from 'react';
import { Button, Col, Form, Modal, Row, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import Dotdotdot from 'react-dotdotdot';
import { toast } from 'react-toastify';
import { BorderColor, Cancel, CheckCircle, Delete } from '@mui/icons-material';

const TableService = (props) => {
  let arrCoppy = [...props.serviceList];
  const [imgLink, setImgLink] = useState('');
  const [showEdit, setEditShow] = useState(false);

  const [serv, setServ] = useState(null);
  let newService;
  const handleEditClose = () => setEditShow(false);
  const handleEditShow = (service) => {
    setEditShow(true);
    setServ(service);
    setImgLink(service.serImage);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    const info = new FormData(event.currentTarget);

    newService = {
      id: info.get('id'),
      serName: info.get('serName'),
      serDescription: info.get('serDescription'),
      serPrice: info.get('serPrice'),
      serIsActive: info.get('serIsActive') === '1' ? true : false,
    };

    let data = JSON.stringify(newService);

    if (document.getElementById('file').files.length > 0) {
      newService.serImage = URL.createObjectURL(info.get('file'));
    } else {
      newService.serImage = imgLink;
    }

    info.append('service', data);
    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/admin/update-service',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
      data: info,
    };

    axios(config)
      .then(function (response) {
        if (response.data.code === 200) {
          toast.success(response.data.message);
          setEditShow(false);
          const idx = arrCoppy.findIndex((value) => value.id === serv.id);
          arrCoppy[idx] = newService;
          props.setService(arrCoppy);
        }
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };

  const [showDelete, setDeleteShow] = useState(false);
  const [serID, setSerID] = useState(0);
  const handleDeleteClose = () => setDeleteShow(false);
  const handleDeleteShow = (id) => {
    setSerID(id);
    setDeleteShow(true);
  };
  const DeleteConfirm = () => {
    var config = {
      method: 'post',
      url: `http://localhost:8989/api/admin/delete-service?id=${serID}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    axios(config)
      .then(function (response) {
        toast.success(response.data.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
        setDeleteShow(false);
        const idx = arrCoppy.findIndex((value) => value.id === serID);
        arrCoppy[idx].serIsActive = 0;
        props.setService(arrCoppy);
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };

  return (
    <>
      <Table striped hover>
        <thead>
          <tr>
            <th className="center">Mã</th>
            <th>Tên</th>
            <th className="center">Giá</th>
            <th className="center">Mô tả</th>
            <th className="center">Trạng thái</th>
            <th className="center">Ảnh</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {props.serviceList.map((service) => (
            <tr key={service.id}>
              <td className="center">{service.id}</td>
              <td>{service.serName}</td>
              <td>
                <FormatPrice price={service.serPrice} />
              </td>
              <td width={300}>
                <Dotdotdot clamp={2}>{service.serDescription}</Dotdotdot>
              </td>
              <td className="center">
                {service.serIsActive ? (
                  <CheckCircle color="success" />
                ) : (
                  <Cancel sx={{ color: 'darkred' }} />
                )}
              </td>
              <td>
                <img
                  width={120}
                  height={70}
                  src={service.serImage}
                  alt={service.serName}
                ></img>
              </td>
              <td>
                <Button
                  style={{ marginRight: '10px' }}
                  variant="outline-primary"
                  onClick={() => handleEditShow(service)}
                >
                  <BorderColor />
                </Button>{' '}
                <Button
                  onClick={() => handleDeleteShow(service.id)}
                  variant="outline-danger"
                >
                  <Delete />
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Modal centered show={showEdit} onHide={handleEditClose}>
        <Modal.Header closeButton>
          <Modal.Title>Chỉnh sửa thông tin</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {serv !== null ? (
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Row>
                  <Col sm={2}>
                    <Form.Label>ID</Form.Label>
                    <Form.Control
                      name="id"
                      type="text"
                      value={serv.id}
                      readOnly
                    />
                  </Col>
                  <Col>
                    <Form.Label>Tên dịch vụ</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={serv.serName}
                      placeholder="Tên dịch vụ"
                      name="serName"
                    />
                  </Col>
                </Row>
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="formBasicPassword"
              ></Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Giá</Form.Label>
                <Form.Control
                  type="number"
                  defaultValue={serv.serPrice}
                  name="serPrice"
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Mô tả</Form.Label>
                <Form.Control
                  as="textarea"
                  defaultValue={serv.serDescription}
                  placeholder="Mô tả"
                  name="serDescription"
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Trạng thái</Form.Label>
                <Form.Select name="serIsActive">
                  {serv.serIsActive ? (
                    <>
                      <option value={1}>Hoạt động</option>
                      <option value={0}>Ngưng hoạt động</option>
                    </>
                  ) : (
                    <>
                      <option value={0}>Ngưng hoạt động</option>
                      <option value={1}>Hoạt động</option>
                    </>
                  )}
                </Form.Select>
              </Form.Group>
              <Form.Group>
                <Form.Label>Ảnh dịch vụ</Form.Label>
                <Form.Control id="file" name="file" type="file" />
              </Form.Group>
              <Form.Group style={{ marginTop: '20px', textAlign: 'center' }}>
                <Button variant="success" type="submit">
                  Lưu thông tin
                </Button>{' '}
                <Button variant="danger" onClick={handleEditClose}>
                  Huỷ
                </Button>
              </Form.Group>
            </Form>
          ) : (
            ''
          )}
        </Modal.Body>
      </Modal>
      <Modal centered show={showDelete} onHide={handleDeleteClose}>
        <Modal.Header closeButton>
          <Modal.Title>Xác nhận xoá</Modal.Title>
        </Modal.Header>
        <Modal.Body>Bạn chắc chắn muốn xoá dịch vụ này?</Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleDeleteClose}>
            Huỷ
          </Button>
          <Button variant="danger" onClick={DeleteConfirm}>
            Xác Nhận
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default TableService;
