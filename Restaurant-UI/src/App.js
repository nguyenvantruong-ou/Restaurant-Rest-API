//client
import ClientLayout from './components/client/ClientLayout';
import Contact from './components/client/contact/Contact';
import Service from './components/client/service/Service';
import Lobby from './components/client/lobby/Lobby';
import Menu from './components/client/menu/Menu';
import LobbyDetail from './components/client/lobby/LobbyDetail';
//admin
import AdminLogin from './components/admin/AdminLogin';
import AdminLayout from './components/admin/AdminLayout';
import Dashboard from './components/admin/Dashboard';
import LobbyManagement from './components/admin/lobby-management/LobbyManagement';
import ServiceManagement from './components/admin/service-mangagement/ServiceManagement';
import UserManagement from './components/admin/user-management/UserManagement';
import BillStats from './components/admin/stats/BillStats';
import OrderStats from './components/admin/stats/OrderStats';
import ListBills from './components/admin/stats/ListBills';
//auth
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import Home from './components/client/Home';
//*
import { Routes, Route } from 'react-router-dom';
import ScrollToTopButton from './components/ScrollToTopButton';
import PageNotFound from './components/ErrorPages/PageNotFound';
import Booking from './components/client/Booking';
import FeedbackAdmin from './components/admin/feedback/feedback';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import HistoryOrder from './components/client/history-order/HistoryOrder';
import ListBooking from './components/client/history-order/ListBooking';
import moment from 'moment';
import 'moment/locale/vi';
import AdminLogin2 from './components/admin/user-management/Adminlogin2';

moment().local('vi');

const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<ClientLayout />}>
          <Route path="/" element={<Home />} />
          <Route path="lobby" element={<Lobby />} />
          <Route path="lobby/details/:id" element={<LobbyDetail />} />
          <Route path="service" element={<Service />} />
          <Route path="service/details" element={<LobbyDetail />} />
          <Route path="contact" element={<Contact />} />
          <Route path="menu" element={<Menu />} />
          <Route path="booking" element={<Booking />} />
          <Route path="history-order" element={<HistoryOrder />} />
          <Route path="list-booking" element={<ListBooking />} />
        </Route>
        <Route path="/admin" element={<AdminLayout />}>
          <Route path="login" element={<AdminLogin />} />
          <Route path="login2" element={<AdminLogin2 />} />
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="" element={<Dashboard />} />
          <Route path="lobby-management" element={<LobbyManagement />} />
          <Route path="service-management" element={<ServiceManagement />} />
          <Route path="user-management" element={<UserManagement />} />
          <Route path="bill-stats" element={<BillStats />} />
          <Route path="order-stats" element={<OrderStats />} />
          <Route path="bills" element={<ListBills />} />
          <Route path="feedback" element={<FeedbackAdmin />} />
        </Route>
        <Route path="/auth">
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
        </Route>
        <Route path="*" element={<PageNotFound />} />
      </Routes>
      <ScrollToTopButton />
      <ToastContainer autoClose={2000} />
    </div>
  );
};

export default App;
