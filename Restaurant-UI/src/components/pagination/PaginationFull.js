import { Visibility } from '@mui/icons-material';
import React, { useEffect, useState } from 'react';
import { Button, Table, Modal } from 'react-bootstrap';
import ReactPaginate from 'react-paginate';
import FormatPrice from '../FormatPrice';
import FormatNoted from './FormatNoted';

const PaginationFull = ({ listItems }) => {
  const items = listItems;
  const [showDetail, setShowDetail] = useState(false);
  const [bill, setBill] = useState(null);
  const handleDetailClose = () => setShowDetail(false);
  const handleDetailShow = (bill) => {
    setShowDetail(true);
    setBill(bill);
  };

  function Items({ currentItems }) {
    return (
      <>
        <Table striped hover style={{ margin: '10px 0' }}>
          <thead>
            <tr>
              <th>Mã</th>
              <th className="center">Ngày thanh toán</th>
              <th className="center">Khách hàng</th>
              <th className="center">Tổng tiền</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {currentItems &&
              currentItems.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td className="center">{item.createDate}</td>
                  <td className="center">{item.userName}</td>
                  <td className="center">
                    <i>
                      <FormatPrice price={item.totalMoney} /> VND
                    </i>
                  </td>
                  <td width={'12%'}>
                    <Button
                      variant="outline-info"
                      onClick={() => handleDetailShow(item)}
                    >
                      <Visibility /> chi tiết
                    </Button>
                  </td>
                </tr>
              ))}
          </tbody>
        </Table>
        <Modal centered show={showDetail} onHide={handleDetailClose}>
          <Modal.Header closeButton>
            <Modal.Title>Chi tiết hoá đơn</Modal.Title>
          </Modal.Header>
          {bill !== null ? (
            <Modal.Body>
              <div className="info-order">
                <div className="center">
                  Mã tiệc: <b>{bill.id}</b>
                </div>
                <div className="center">
                  Họ & tên khách hàng: <b>{bill.userName}</b>
                </div>
                <div className="center">
                  Ngày tổ chức: <b>{bill.createDate}</b>
                </div>
                <div className="center">
                  Tổng chi phí:{' '}
                  <i>
                    <b>
                      <FormatPrice price={bill.totalMoney} /> VND
                    </b>
                  </i>
                </div>
              </div>
              <div>
                <b>Các dịch vụ chọn kèm: </b>
              </div>
              <div>
                <FormatNoted note={bill.note} />
              </div>
            </Modal.Body>
          ) : (
            ''
          )}
        </Modal>
      </>
    );
  }
  function PaginatedItems({ itemsPerPage }) {
    const [currentItems, setCurrentItems] = useState(null);
    const [pageCount, setPageCount] = useState(0);
    const [itemOffset, setItemOffset] = useState(0);

    useEffect(() => {
      const endOffset = itemOffset + itemsPerPage;
      setCurrentItems(items.slice(itemOffset, endOffset));
      setPageCount(Math.ceil(items.length / itemsPerPage));
    }, [itemOffset, itemsPerPage]);

    const handlePageClick = (event) => {
      const newOffset = (event.selected * itemsPerPage) % items.length;
      setItemOffset(newOffset);
    };

    return (
      <>
        <Items currentItems={currentItems} />
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
        />
      </>
    );
  }
  return (
    <>
      <PaginatedItems itemsPerPage={10} />
    </>
  );
};

export default PaginationFull;
