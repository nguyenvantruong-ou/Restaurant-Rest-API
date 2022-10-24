import * as React from 'react';
import { Table } from 'react-bootstrap';
import FormatPrice from '../../FormatPrice';

const TableStats = (props) => {
  return (
    <Table striped hover>
      <thead>
        <tr>
          <th className="center">Tháng</th>
          <th className="center">Năm</th>
          <th className="center">{props.title}</th>
        </tr>
      </thead>
      <tbody>
        {props.data.map((item) => (
          <tr key={item.month + '/' + item.year}>
            <td className="center">{item.month}</td>
            <td className="center">{item.year}</td>
            <td className="center">
              <FormatPrice price={item[props.typeStats]} /> {props.unit}
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TableStats;
