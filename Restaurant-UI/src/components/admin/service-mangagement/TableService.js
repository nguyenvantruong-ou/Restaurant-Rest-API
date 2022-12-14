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
            <th className="center">M??</th>
            <th>T??n</th>
            <th className="center">Gi??</th>
            <th className="center">M?? t???</th>
            <th className="center">Tr???ng th??i</th>
            <th className="center">???nh</th>
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
          <Modal.Title>Ch???nh s???a th??ng tin</Modal.Title>
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
                    <Form.Label>T??n d???ch v???</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={serv.serName}
                      placeholder="T??n d???ch v???"
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
                <Form.Label>Gi??</Form.Label>
                <Form.Control
                  type="number"
                  defaultValue={serv.serPrice}
                  name="serPrice"
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>M?? t???</Form.Label>
                <Form.Control
                  as="textarea"
                  defaultValue={serv.serDescription}
                  placeholder="M?? t???"
                  name="serDescription"
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Tr???ng th??i</Form.Label>
                <Form.Select name="serIsActive">
                  {serv.serIsActive ? (
                    <>
                      <option value={1}>Ho???t ?????ng</option>
                      <option value={0}>Ng??ng ho???t ?????ng</option>
                    </>
                  ) : (
                    <>
                      <option value={0}>Ng??ng ho???t ?????ng</option>
                      <option value={1}>Ho???t ?????ng</option>
                    </>
                  )}
                </Form.Select>
              </Form.Group>
              <Form.Group>
                <Form.Label>???nh d???ch v???</Form.Label>
                <Form.Control id="file" name="file" type="file" />
              </Form.Group>
              <Form.Group style={{ marginTop: '20px', textAlign: 'center' }}>
                <Button variant="success" type="submit">
                  L??u th??ng tin
                </Button>{' '}
                <Button variant="danger" onClick={handleEditClose}>
                  Hu???
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
          <Modal.Title>X??c nh???n xo??</Modal.Title>
        </Modal.Header>
        <Modal.Body>B???n ch???c ch???n mu???n xo?? d???ch v??? n??y?</Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleDeleteClose}>
            Hu???
          </Button>
          <Button variant="danger" onClick={DeleteConfirm}>
            X??c Nh???n
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default TableService;
