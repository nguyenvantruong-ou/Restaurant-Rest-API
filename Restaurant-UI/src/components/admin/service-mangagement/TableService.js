import axios from 'axios';
import React, { useEffect } from 'react';
import { useState } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import Dotdotdot from 'react-dotdotdot';
import { toast } from 'react-toastify';

const TableService = (props) => {
  let arrCoppy = [...props.serviceList];
  const [showEdit, setEditShow] = useState(false);

  const handleEditClose = () => setEditShow(false);
  const handleEditShow = () => {
    setEditShow(true);
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
            <th>Mã</th>
            <th className="center">Tên</th>
            <th className="center">Giá</th>
            <th className="center">Mô tả</th>
            <th className="center">Trạng thái</th>
            <th className="center">Ảnh</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {props.serviceList.map((service) => (
            <tr key={service.id}>
              <td>{service.id}</td>
              <td>{service.serName}</td>
              <td>
                <FormatPrice price={service.serPrice} />
              </td>
              <td width={300}>
                <Dotdotdot clamp={2}>{service.serDescription}</Dotdotdot>
              </td>
              <td>{service.serIsActive ? 'hoạt động' : 'ngưng hoạt động'}</td>
              <td>
                <img
                  width={120}
                  height={70}
                  src={service.serImage}
                  alt={service.serName}
                ></img>
              </td>
              <td>
                <Button onClick={handleEditShow}>Sửa</Button>
              </td>
              <td>
                <Button
                  onClick={() => handleDeleteShow(service.id)}
                  variant="danger"
                >
                  Xoá
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Modal show={showEdit} onHide={handleEditClose}>
        <Modal.Header closeButton>
          <Modal.Title>Chỉnh sửa thông tin</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={handleEditClose}>
            Huỷ
          </Button>
          <Button variant="success" onClick={handleEditClose}>
            Lưu
          </Button>
        </Modal.Footer>
      </Modal>
      <Modal show={showDelete} onHide={handleDeleteClose}>
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
