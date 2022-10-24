import React from 'react';
import HomeIcon from '@mui/icons-material/HomeMax';
import FoodBankIcon from '@mui/icons-material/FoodBank';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';
import HomeRepairServiceIcon from '@mui/icons-material/HomeRepairService';
import QueryStatsIcon from '@mui/icons-material/QueryStats';
import LeaderboardIcon from '@mui/icons-material/Leaderboard';
import FileCopyIcon from '@mui/icons-material/FileCopy';
import LogoutIcon from '@mui/icons-material/Logout';
import { Campaign } from '@mui/icons-material';

const menuItem = [
  {
    path: '/admin',
    name: 'Trang chủ',
    icon: <HomeIcon />,
  },
  {
    path: '/admin/user-management',
    name: 'Quản lí người dùng',
    icon: <PeopleAltIcon />,
  },
  {
    path: '/admin/lobby-management',
    name: 'Quản lí sảnh cưới',
    icon: <FoodBankIcon />,
  },
  {
    path: '/admin/service-management',
    name: 'Quản lí dịch vụ',
    icon: <HomeRepairServiceIcon />,
  },
  {
    path: '/admin/order-stats',
    name: 'Thống kê mật độ',
    icon: <QueryStatsIcon />,
  },
  {
    path: '/admin/bill-stats',
    name: 'Thống kê doanh thu',
    icon: <LeaderboardIcon />,
  },
  {
    path: '/admin/bills',
    name: 'Danh sách hoá đơn',
    icon: <FileCopyIcon />,
  },
  {
    path: '/admin/feedback',
    name: 'Phàn hồi từ khách hàng',
    icon: <Campaign />,
  },
  {
    path: '/admin/login',
    name: 'Logout',
    icon: <LogoutIcon />,
  },
];

export default menuItem;
