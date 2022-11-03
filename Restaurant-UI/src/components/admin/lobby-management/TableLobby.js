import React from 'react';
import { useState } from 'react';
import { Button, Col, Form, Modal, Row, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import Dotdotdot from 'react-dotdotdot';
import axios from 'axios';
import { toast } from 'react-toastify';
import { BorderColor, Cancel, CheckCircle, Delete } from '@mui/icons-material';

const TableLobby = (props) => {
  let arrCoppy = [...props.listLobby];
  const [showEdit, setEditShow] = useState(false);
  const [lobb, setLobb] = useState(null);
  const [imgLink, setImgLink] = useState('');
  let newLobby;

  const handleEditClose = () => setEditShow(false);
  const handleEditShow = (lobby) => {
    setEditShow(true);
    setLobb(lobby);
    setImgLink(lobby.lobImage);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    const info = new FormData(event.currentTarget);

    newLobby = {
      id: info.get('id'),
      lobName: info.get('lobName'),
      lobDescription: info.get('lobDescription'),
      lobPrice: info.get('lobPrice'),
      lobTotalTable: info.get('lobTotalTable'),
      lobAddress: info.get('lobAddress'),
      lobIsActive: info.get('lobIsActive') === '1' ? true : false,
    };

    let data = JSON.stringify(newLobby);

    if (document.getElementById('file').files.length > 0) {
      newLobby.lobImage = URL.createObjectURL(info.get('file'));
    } else {
      newLobby.lobImage = imgLink;
    }

    info.append('lobby', data);
    info.append('listFile', info.get('listFile'));
    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/admin/update-lobby',
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
          const idx = arrCoppy.findIndex((value) => value.id === lobb.id);
          arrCoppy[idx] = newLobby;
          props.setLobby(arrCoppy);
        }
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };

  const [lobID, setLobID] = useState(0);
  const [showDelete, setDeleteShow] = useState(false);

  const handleDeleteClose = () => setDeleteShow(false);
  const handleDeleteShow = (id) => {
    setLobID(id);
    setDeleteShow(true);
  };
  const DeleteConfirm = () => {
    var config = {
      method: 'post',
      url: `http://localhost:8989/api/admin/delete-lobby?id=${lobID}`,
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
        const idx = arrCoppy.findIndex((value) => value.id === lobID);
        arrCoppy[idx].lobIsActive = 0;
        props.setLobby(arrCoppy);
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
            <th>Mã</th>
            <th width={150}>Tên</th>
            <th className="center">Giá</th>
            <th className="center">Số bàn</th>
            <th className="center">Địa chỉ</th>
            <th className="center">Mô tả</th>
            <th className="center">Trạng thái</th>
            <th className="center">Ảnh</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {props.listLobby.map((lobby) => (
            <tr key={lobby.id}>
              <td>{lobby.id}</td>
              <td>{lobby.lobName}</td>
              <td className="center">
                <FormatPrice price={lobby.lobPrice} />
              </td>
              <td className="center">{lobby.lobTotalTable}</td>
              <td className="center">{lobby.lobAddress}</td>
              <td width={300}>
                <Dotdotdot clamp={3}>{lobby.lobDescription}</Dotdotdot>
              </td>
              <td className="center">
                {lobby.lobIsActive ? (
                  <CheckCircle color="success" />
                ) : (
                  <Cancel sx={{ color: 'darkred' }} />
                )}
              </td>
              <td className="center">
                <img
                  width={120}
                  height={100}
                  src={lobby.lobImage}
                  alt={lobby.lobName}
                ></img>
              </td>
              <td className="center">
                <Button
                  style={{ marginRight: '10px' }}
                  variant="outline-primary"
                  onClick={() => handleEditShow(lobby)}
                >
                  <BorderColor />
                </Button>
                <Button
                  onClick={() => handleDeleteShow(lobby.id)}
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
          {lobb !== null ? (
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Row>
                  <Col sm={2}>
                    <Form.Label>ID</Form.Label>
                    <Form.Control
                      name="id"
                      type="text"
                      value={lobb.id}
                      readOnly
                    />
                  </Col>
                  <Col>
                    <Form.Label>Tên sảnh tiệc</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={lobb.lobName}
                      placeholder="Tên dịch vụ"
                      name="lobName"
                    />
                  </Col>
                </Row>
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Row>
                  <Col sm={7}>
                    <Form.Label>Giá</Form.Label>
                    <Form.Control
                      type="number"
                      defaultValue={lobb.lobPrice}
                      name="lobPrice"
                    />
                  </Col>
                  <Col>
                    <Form.Label>Số bàn</Form.Label>
                    <Form.Control
                      type="number"
                      defaultValue={lobb.lobTotalTable}
                      name="lobTotalTable"
                    />
                  </Col>
                </Row>
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="formBasicPassword"
              ></Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Địa chỉ</Form.Label>
                <Form.Control
                  type="text"
                  defaultValue={lobb.lobAddress}
                  name="lobAddress"
                />
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="formBasicPassword"
              ></Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Mô tả</Form.Label>
                <Form.Control
                  as="textarea"
                  rows={5}
                  defaultValue={lobb.lobDescription}
                  placeholder="Mô tả"
                  name="lobDescription"
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Trạng thái</Form.Label>
                <Form.Select name="lobIsActive">
                  {lobb.lobIsActive ? (
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
              <Form.Group>
                <Form.Label>Danh sách ảnh thêm</Form.Label>
                <Form.Control
                  id="listFile"
                  name="listFile"
                  type="file"
                  multiple
                />
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
        <Modal.Body>Bạn có chắc xoá sảnh không?</Modal.Body>
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

export default TableLobby;
