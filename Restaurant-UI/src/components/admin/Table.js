import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';

const columns = [
  { id: 'id', label: 'ID', minWidth: 170 },
  { id: 'firstName', label: 'Họ và đệm', minWidth: 100 },
  {
    id: 'lastName',
    label: 'Tên',
    minWidth: 170,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'role',
    label: 'Role',
    minWidth: 170,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'density',
    label: 'Density',
    minWidth: 170,
    align: 'right',
    format: (value) => value.toFixed(2),
  },
];

function createData(id, firstName, lastName, role) {
  const density = lastName / role;
  return { id, firstName, lastName, role, density };
}

const rows = [
  createData('1', 'Nguyễn Văn', 'Trường', 'ADMIN'),
  createData('2', 'Lê Thị Như', 'Ý', 'USER'),
  createData('3', 'Nguyễn Duy', 'Tân', 'USER'),
  createData('4', 'Lý Nhã', 'Kỳ', 'USER'),
  createData('5', 'Lê Cát', 'Anh', 'USER'),
  createData('6', 'Cao Bá', 'Kiệt', 'USER'),
  createData('7', 'Nguyễn Minh', 'Hiếu', 'USER'),
  createData('8', 'Bùi Mạnh', 'Linh', 'USER'),
  createData('9', 'Huỳnh Anh', 'Tuấn', 'USER'),
  createData('10', 'Nguyễn Thành', 'Danh', 'ADmin'),
  createData('11', 'FR', 67022000, 640679),
  createData('12', 'GB', 67545757, 242495),
  createData('13', 'RU', 146793744, 17098246),
  createData('14', 'NG', 200962417, 923768),
  createData('15', 'BR', 210147125, 8515767),
];

export default function StickyHeadTable() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                    {columns.map((column) => {
                      const value = row[column.id];
                      return (
                        <TableCell key={column.id} align={column.align}>
                          {column.format && typeof value === 'number'
                            ? column.format(value)
                            : value}
                        </TableCell>
                      );
                    })}
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
