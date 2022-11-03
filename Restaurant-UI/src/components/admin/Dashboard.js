import Error403 from '../ErrorPages/Error403';

const Dashboard = () => {
  const role = localStorage.getItem('role');
  if (role === 'ADMIN') return <div className="dashboard-img"></div>;
  else return <Error403 links={'/admin/login'} />;
};

export default Dashboard;
