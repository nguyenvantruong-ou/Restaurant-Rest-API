import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);
function random_rgba() {
  let o = Math.round,
    r = Math.random,
    s = 255;
  return (
    'rgba(' + o(r() * s) + ',' + o(r() * s) + ',' + o(r() * s) + ',' + 0.3 + ')'
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
        label: props.title,
        data: datas,
        backgroundColor: colors,
        borderColor: colors,
        borderWidth: 0.7,
      },
    ],
  };

  return (
    <Doughnut
      data={data}
      width={2}
      height={'300px'}
      options={{ maintainAspectRatio: false }}
    />
  );
};

export default StatsChart;
