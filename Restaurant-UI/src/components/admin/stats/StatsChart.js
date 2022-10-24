import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);
function random_rgba() {
  let o = Math.round,
    r = Math.random,
    s = 255;
  return (
    'rgb(' + o(r() * s) + ',' + o(r() * s) + ',' + o(r() * s) + ',' + 0.6 + ')'
  );
}

const StatsChart = (props) => {
  const orderStats = props.data;
  const labels = orderStats.map((item) => item.month + '/' + item.year);
  const datas = orderStats.map((item) => item[props.typeStats]);
  const colors = [];
  for (let i = 0; i < datas.length; i += 1) {
    colors.push(random_rgba());
  }
  const data = {
    labels: labels,
    datasets: [
      {
        label: 'Thống kê mật độ đặt tiệc',
        data: datas,
        backgroundColor: colors,
        borderColor: colors,
        borderWidth: 0.4,
      },
    ],
  };

  return (
    <Pie
      data={data}
      width={2}
      height={'300px'}
      options={{ maintainAspectRatio: false }}
    />
  );
};

export default StatsChart;
