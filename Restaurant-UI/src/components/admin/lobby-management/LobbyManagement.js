import React, { useEffect, useState } from 'react';
import Title from '../Title';
import TableLobby from './TableLobby';
import Error403 from '../../ErrorPages/Error403';
import axios from 'axios';
import { Button, Col, Form, Modal, Row } from 'react-bootstrap';
import { DomainAdd } from '@mui/icons-material';
import ReactPaginate from 'react-paginate';
import { toast } from 'react-toastify';

const LobbyManagement = () => {
  const [lobbyData, setLobbyData] = useState([]);
  const [numPage, setNumPage] = useState([]);
  const [page, setPage] = useState('1');
  const role = localStorage.getItem('role');
  const [keyWord, setKeyWord] = useState('');
  const [currentPageClick, setCurrentPageClick] = useState(0);
  const [showAdd, setAddShow] = useState(false);
  const handleAddClose = () => setAddShow(false);
  const handleAddShow = () => {
    setAddShow(true);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    const info = new FormData(event.currentTarget);

    const newLobby = {
      lobName: info.get('lobName'),
      lobDescription: info.get('lobDescription'),
      lobPrice: info.get('lobPrice'),
      lobTotalTable: info.get('lobTotalTable'),
      lobAddress: info.get('lobAddress'),
    };

    let data = JSON.stringify(newLobby);
    info.append('lobby', data);
    info.append('listFile', info.get('listFile'));
    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/admin/create-lobby',
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
    setCurrentPageClick(0);
    setKeyWord(document.getElementById('search').value.trim());
  };
  useEffect(() => {
    let config = {
      method: 'get',
      url: `http://localhost:8989/api/admin/get-list-lobby?size=4&page=${page}&kw=${keyWord}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setLobbyData(res.data.listLobby);
        setNumPage(res.data.numberPage);
      })
      .catch(function (error) {
        toast.error(error.message);
      });
  }, [keyWord, page]);
  function PaginatedItems() {
    const [pageCount, setPageCount] = useState(0);
    useEffect(() => {
      setPageCount(numPage);
    }, []);

    const handlePageClick = (event) => {
      setPage(event.selected + 1);
      setCurrentPageClick(event.selected);
    };

    return (
      <>
        <ReactPaginate
          style={{ color: 'red' }}
          nextLabel=">>"
          onPageChange={handlePageClick}
          pageRangeDisplayed={3}
          marginPagesDisplayed={2}
          pageCount={pageCount}
          previousLabel="<<"
          pageClassName="page-item"
          pageLinkClassName="page-link"
          previousClassName="page-item"
          previousLinkClassName="page-link"
          nextClassName="page-item"
          nextLinkClassName="page-link"
          breakLabel="..."
          breakClassName="page-item"
          breakLinkClassName="page-link"
          containerClassName="pagination"
          activeClassName="active"
          renderOnZeroPageCount={null}
          initialPage={currentPageClick}
        />
      </>
    );
  }
  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Quản lý sảnh cưới'} />
        <div className="box-button">
          <Button
            className="btn-add"
            onClick={handleAddShow}
            variant="outline-success"
          >
            <DomainAdd />
            Thêm mới sảnh
          </Button>
          <input
            id="search"
            className="search search-admin"
            placeholder="Nhập tên sảnh tiệc..."
            onChange={handleSearch}
          ></input>
        </div>
        <TableLobby listLobby={lobbyData} setLobby={setLobbyData} />
        <PaginatedItems />
        <Modal centered show={showAdd} onHide={handleAddClose}>
          <Modal.Header closeButton>
            <Modal.Title>Thêm mới sảnh tiệc</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Tên sảnh tiệc</Form.Label>
                <Form.Control type="text" name="lobName" />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Row>
                  <Col sm={7}>
                    <Form.Label>Giá</Form.Label>
                    <Form.Control type="number" name="lobPrice" />
                  </Col>
                  <Col>
                    <Form.Label>Số bàn</Form.Label>
                    <Form.Control type="number" name="lobTotalTable" />
                  </Col>
                </Row>
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="formBasicPassword"
              ></Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Địa chỉ</Form.Label>
                <Form.Control type="text" name="lobAddress" />
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
                  placeholder="Mô tả"
                  name="lobDescription"
                />
              </Form.Group>
              <Form.Group>
                <Form.Label>Ảnh sảnh tiệc </Form.Label>
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
                  Thêm mới sảnh
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

export default LobbyManagement;
