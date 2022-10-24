import React from 'react';
import { useState } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';
import Dotdotdot from 'react-dotdotdot';
import axios from 'axios';
import { toast } from 'react-toastify';

const TableLobby = (props) => {
  let arrCoppy = [...props.listLobby];
  const [showEdit, setEditShow] = useState(false);

  const handleEditClose = () => setEditShow(false);
  const handleEditShow = () => {
    setEditShow(true);
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
            <th className="center">Mô tả</th>
            <th className="center">Trạng thái</th>
            <th className="center">Ảnh</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {props.listLobby.map((lobby) => (
            <tr key={lobby.id}>
              <td>{lobby.id}</td>
              <td>{lobby.lobName}</td>
              <td>
                <FormatPrice price={lobby.lobPrice} />
              </td>

              <td width={400}>
                <Dotdotdot clamp={3}>{lobby.lobDescription}</Dotdotdot>
              </td>
              <td>{lobby.lobIsActive ? 'hoạt động' : 'ngưng hoạt động'}</td>
              <td>
                <img
                  width={120}
                  height={100}
                  src={lobby.lobImage}
                  alt={lobby.lobName}
                ></img>
              </td>
              <td className="center">
                <Button onClick={handleEditShow}>Sửa</Button>
              </td>
              <td className="center">
                <Button
                  onClick={() => handleDeleteShow(lobby.id)}
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
