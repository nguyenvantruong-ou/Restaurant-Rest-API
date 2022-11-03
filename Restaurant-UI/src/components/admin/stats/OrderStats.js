import React, { useEffect, useState } from 'react';
import Title from '../Title';
import TableStats from './TableStats';
import StatsChart from './StatsChart';
import axios from 'axios';
import { toast } from 'react-toastify';
import Error403 from '../../ErrorPages/Error403';
import { Button, Form } from 'react-bootstrap';

const OrderMonthStats = () => {
  const role = localStorage.getItem('role');
  const [fromDate, setFromDate] = useState('2020-01-01');
  const [toDate, setToDate] = useState('2022-10-24');
  const btnStatsClick = () => {
    const fromDate = document.getElementById('from-date').value;
    const toDate = document.getElementById('to-date').value;
    if (fromDate > toDate) {
      toast.error('khoảng thời gian sai');
    } else {
      setFromDate(fromDate);
      setToDate(toDate);
    }
  };
  let [data, setData] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: `http://localhost:8989/api/admin/report-amount-order-by-year?fromDate=${fromDate}&toDate=${toDate}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setData(res.data);
        toast.success(response.data.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      })
      .catch(function (error) {
        toast.error(error.message, {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  }, [fromDate, toDate]);
  if (role === 'ADMIN') {
    return (
      <>
        <Title title={'Thống kê mật độ'} />
        <div className="box-stats-date">
          <label for="from-date">Từ ngày:</label>
          <input
            type="date"
            id="from-date"
            name="from-date"
            defaultValue="2020-01-01"
          />
          <label for="to-date">đến ngày:</label>
          <input
            type="date"
            id="to-date"
            name="to-date"
            defaultValue="2022-10-24"
          />
          <Button
            style={{ marginLeft: '10px' }}
            variant="outline-success"
            onClick={btnStatsClick}
          >
            Thống kê
          </Button>
        </div>
        {data !== null ? (
          <>
            {' '}
            <div className="box-stats">
              <div className="chart">
                <StatsChart
                  data={data}
                  title="Thống kê mật độ"
                  typeStats="amountOrder"
                />
              </div>
              <div className="table-info">
                <TableStats
                  data={data}
                  title="Tổng tiệc"
                  typeStats="amountOrder"
                  unit="(tiệc)"
                />
              </div>
            </div>
          </>
        ) : (
          ''
        )}
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

export default OrderMonthStats;
