import React from 'react';
import { Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';

const TableBill = (props) => {
  return (
    <Table striped hover>
      <thead>
        <tr>
          <th>Mã</th>
          <th className="center">Ngày thanh toán</th>
          <th className="center">Khách hàng</th>
          <th className="center">Tổng tiền</th>
        </tr>
      </thead>
      <tbody>
        {props.listBills.map((bill) => (
          <tr key={bill.id}>
            <td>{bill.id}</td>
            <td className="center">{bill.createDate}</td>
            <td className="center">{bill.userName}</td>
            <td className="center">
              <i>
                <FormatPrice price={bill.totalMoney} /> VND
              </i>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TableBill;
