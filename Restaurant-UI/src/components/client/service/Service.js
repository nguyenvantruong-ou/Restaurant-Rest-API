import { Typography } from '@mui/material';
import React from 'react';
import { Container } from '@mui/system';
import Banner from '../Banner';
import TitlePage from '../TitlePage';
import ServiceList from './ServiceList';
import { CameraAlt } from '@mui/icons-material';
import DishCarousel from '../dish-carousel/DishCarousel';

const bannerURL =
  'https://res.cloudinary.com/dqifjhxxg/image/upload/v1661664980/restaurant%20management/banner/service-header_pnhkwg.jpg';

const Service = () => {
  return (
    <>
      <Banner imgURL={bannerURL} />
      <Container>
        <div style={{ textAlign: 'center', marginTop: 20 }}>
          <CameraAlt sx={{ color: '#dd9933', fontSize: 60 }} />
        </div>
        <TitlePage title="các dịch vụ tại thành văn" />
        <Typography
          variant="h7"
          sx={{
            marginTop: 3,
            marginLeft: 4,
            color: '#646464',
          }}
        >
          Với mong muốn góp phần tô vẽ nên chuyện tình yêu đẹp của lứa đôi,
          <b>
            {' '}
            Trung Tâm Tiệc Cưới Hội Nghị Thành Văn hân hạnh được phục vụ tiệc
            cưới của Bạn
          </b>
        </Typography>
        <ServiceList />
      </Container>
      <DishCarousel />
    </>
  );
};

export default Service;
