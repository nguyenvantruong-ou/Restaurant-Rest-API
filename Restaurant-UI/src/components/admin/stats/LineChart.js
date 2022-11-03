import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

const LineChart2 = (props) => {
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: props.title,
      },
    },
  };

  const labels = props.data.map((item) => item.month + '/' + item.year);
  const data = {
    labels,
    datasets: [
      {
        fill: true,
        label: props.title,
        data: props.data.map((item) => item[props.typeStats]),
        borderColor: 'rgb(53, 162, 235)',
        backgroundColor: 'rgba(53, 162, 235, 0.5)',
      },
    ],
  };
  return <Line options={options} data={data} />;
};

export default LineChart2;
