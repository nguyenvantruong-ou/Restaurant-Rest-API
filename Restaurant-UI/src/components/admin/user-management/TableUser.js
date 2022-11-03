import { BorderColor, Cancel, CheckCircle, Delete } from '@mui/icons-material';
import React from 'react';
import { useState } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';

const TableUser = (props) => {
  const [showEdit, setEditShow] = useState(false);

  const handleEditClose = () => setEditShow(false);
  const handleEditShow = () => {
    setEditShow(true);
  };

  const [showDelete, setDeleteShow] = useState(false);

  const handleDeleteClose = () => setDeleteShow(false);
  const handleDeleteShow = () => {
    setDeleteShow(true);
  };

  return (
    <>
      <Table striped hover>
        <thead>
          <tr>
            <th>Mã</th>
            <th>Họ và tên</th>
            <th className="center">Giới tính</th>
            <th className="center">Số CCCD/CMND</th>
            <th className="center">Role</th>
            <th className="center">Trạng thái</th>
            <th className="center">Ảnh</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {props.userData.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>
                {user.userLastName} {user.userFirstName}
              </td>
              <td className="center">{user.userSex ? 'Nam' : 'Nữ'}</td>
              <td className="center">{user.userIdCard}</td>
              <td className="center">{user.userRole}</td>
              <td className="center">
                {user.userIsActive ? (
                  <CheckCircle color="success" />
                ) : (
                  <Cancel sx={{ color: 'darkred' }} />
                )}
              </td>
              <td className="center">
                <img
                  width={50}
                  height={50}
                  className="border-radius"
                  src={user.userImage}
                  alt={user.id}
                ></img>
              </td>
              <td className="center">
                <Button
                  style={{ marginRight: '10px' }}
                  variant="outline-primary"
                  onClick={handleEditShow}
                >
                  <BorderColor />
                </Button>
                <Button onClick={handleDeleteShow} variant="outline-danger">
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
      <Modal centered show={showDelete} onHide={handleDeleteClose}>
        <Modal.Header closeButton>
          <Modal.Title>Xác nhận xoá</Modal.Title>
        </Modal.Header>
        <Modal.Body>Bạn có chắc xoá người dùng không?</Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleDeleteClose}>
            Huỷ
          </Button>
          <Button variant="danger" onClick={handleDeleteClose}>
            Xác Nhận
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default TableUser;
