import { Visibility } from '@mui/icons-material';
import React from 'react';
import { Button, Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';

const TableBill = (props) => {
  return (
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
            <td width={'12%'}>
              <Button variant="outline-info">
                <Visibility /> chi tiết
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TableBill;
