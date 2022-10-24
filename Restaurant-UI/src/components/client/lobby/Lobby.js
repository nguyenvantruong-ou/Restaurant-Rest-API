import { Deck } from '@mui/icons-material';
import { Container } from '@mui/system';
import React from 'react';
import Banner from '../Banner';
import TitlePage from '../TitlePage';

import LobbyList from './LobbyList';

const imageUrl =
  'https://res.cloudinary.com/dqifjhxxg/image/upload/v1663957744/restaurant%20management/lobby/sanh2-1_hayl6p.jpg';

const Lobby = () => {
  return (
    <>
      <Banner imgURL={imageUrl} />
      <div style={{ textAlign: 'center', marginTop: 20 }}>
        <Deck sx={{ color: '#dd9933', fontSize: 60 }} />
      </div>
      <Container>
        <TitlePage title="các khu vực sảnh tiệc" />
        <div
          style={{
            textAlign: 'center',
            marginTop: 20,
            paddingLeft: 100,
            paddingRight: 100,
          }}
        >
          Tất cả sảnh tiệc đều nằm trên mặt bằng trệt, hoàn toàn không sợ kẹt
          thang máy (kẹt thang máy khi di chuyển có thể làm khách mời bỏ lỡ thời
          điểm lễ cưới của bạn diễn ra) và cực kỳ an toàn, tiện lợi.
        </div>
        <div>
          <img
            alt="map"
            style={{ height: 850 }}
            src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663990746/restaurant%20management/lobby/sodonhahang-01-1_ntyzgf.jpg"
          />
        </div>
        <LobbyList />
      </Container>
    </>
  );
};

export default Lobby;
