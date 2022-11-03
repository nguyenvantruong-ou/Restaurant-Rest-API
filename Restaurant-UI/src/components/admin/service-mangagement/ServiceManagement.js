import React from 'react';
import Title from '../Title';
import TableService from './TableService';
import { useState } from 'react';
import { useEffect } from 'react';
import axios from 'axios';
import Error403 from '../../ErrorPages/Error403';
import { Button, Form, Modal } from 'react-bootstrap';
import { NoteAdd } from '@mui/icons-material';
import { toast } from 'react-toastify';

const ServiceManagement = () => {
  const [serviceData, setServiceData] = useState([]);
  const role = localStorage.getItem('role');
  const [keyWord, setKeyWord] = useState('');
  const [showAdd, setAddShow] = useState(false);
  const handleAddClose = () => setAddShow(false);
  const handleAddShow = () => {
    setAddShow(true);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    const info = new FormData(event.currentTarget);

    const newService = {
      serName: info.get('serName'),
      serDescription: info.get('serDescription'),
      serPrice: info.get('serPrice'),
    };

    let data = JSON.stringify(newService);
    info.append('service', data);
    info.append('listFile', info.get('listFile'));
    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/admin/create-service',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
      data: info,
    };

    axios(config)
      .then(function (response) {
        console.log(response.data);
        if (response.data.code === 200) {
          toast.success(response.data.message);
          setAddShow(false);
        } else {
          toast.info(response.data.message);
        }
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };
  const handleSearch = () => {
    setKeyWord(document.getElementById('search').value.trim());
  };
  useEffect(() => {
    let config = {
      method: 'get',
      url: `http://localhost:8989/api/admin/get-list-service?kw=${keyWord}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setServiceData(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [keyWord]);

  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Quản lý dịch vụ'} />
        <div className="box-button">
          <Button
            className="btn-add"
            onClick={handleAddShow}
            variant="outline-success"
          >
            <NoteAdd></NoteAdd>
            Thêm mới dịch vụ
          </Button>
          <input
            id="search"
            className="search search-admin"
            placeholder="Nhập tên dịch vụ..."
            onChange={handleSearch}
          />
        </div>
        <TableService serviceList={serviceData} setService={setServiceData} />
        <Modal centered show={showAdd} onHide={handleAddClose}>
          <Modal.Header closeButton>
            <Modal.Title>Thêm mới dịch vụ</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Tên dịch vụ</Form.Label>
                <Form.Control type="text" name="serName" />
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="formBasicPassword"
              ></Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Giá</Form.Label>
                <Form.Control type="number" name="serPrice" />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Mô tả</Form.Label>
                <Form.Control
                  as="textarea"
                  placeholder="Mô tả"
                  name="serDescription"
                />
              </Form.Group>
              <Form.Group>
                <Form.Label>Ảnh dịch vụ</Form.Label>
                <Form.Control id="file" name="file" type="file" />
              </Form.Group>
              <Form.Group style={{ marginTop: '20px', textAlign: 'center' }}>
                <Button variant="success" type="submit">
                  Lưu thông tin
                </Button>{' '}
                <Button variant="danger" onClick={handleAddClose}>
                  Huỷ
                </Button>
              </Form.Group>
            </Form>
          </Modal.Body>
        </Modal>
      </>
    );
  } else {
    return (
      <>
        <Error403 links={'/admin/login'} />
      </>
    );
  }
};

export default ServiceManagement;
