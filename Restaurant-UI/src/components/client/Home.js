import { Grid, Paper } from '@mui/material';
import { Container } from '@mui/system';
import React from 'react';
import LobbyCarousel from './lobby/LobbyCarousel';
import TitlePage from './TitlePage';

const Home = () => {
  return (
    <div>
      <LobbyCarousel />
      <Container>
        <TitlePage title="Nhà hàng thành văn" />
        <p style={{ textAlign: 'center', paddingLeft: 140, paddingRight: 140 }}>
          Sự hòa quyện giữa nét khỏe khoắn và mềm mại của lối kiến trúc Hy Lạp
          cổ đại, mang đến một không gian ấm cúng, thân tình, nhà hàng tiệc cưới
          Melisa Center như một tòa lâu đài cổ tích ở xứ sở thần tiên, mang
          trong mình sứ mệnh thiêng liêng, gắn kết nhân duyên cho bao lứa đôi
          hạnh phúc. Là nhà hàng tiệc cưới{' '}
          <i style={{ fontWeight: 1000 }}>
            sang trọng và tiện nghi bậc nhất khu vực phía tây{' '}
          </i>{' '}
          thành phố Hồ Chí Minh, 9.000 m2 dành cho sảnh và 4000m2 dành riêng cho
          chỗ đậu xe,
        </p>
      </Container>
      <div
        style={{
          textAlign: 'center',
          backgroundColor: '#d3ac2b',
          padding: 30,
          fontSize: 32,
          fontStyle: 'italic',
          color: '#fff',
          fontFamily: 'Dancing Script',
        }}
      >
        Nhà hàng Thành Văn nơi tôn vinh sự kiện của Bạn
      </div>
      <Container>
        <TitlePage title="ƯU ĐÃI MỚI NHẤT TẠI Thành văn" />
        <h5 style={{ textAlign: 'center' }}>
          Chương trình Ưu đãi tiệc cưới và Ưu đãi hội nghị đang được áp dụng tại
          nhà hàng Thành Văn
        </h5>
        <Grid container spacing={10} sx={{ marginBottom: 2 }}>
          <Grid item xs={6}>
            <Paper elevation={10}>
              <img
                alt="banner-promo"
                className="promo"
                style={{ width: 600 }}
                src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663954169/restaurant%20management/BANNER-WED-1-5-1536x576_rln1pt.jpg"
              />
            </Paper>
          </Grid>
          <Grid item xs={6}>
            <Paper elevation={10}>
              <img
                alt="banner-promo"
                className="promo"
                style={{ width: 600 }}
                src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663954175/restaurant%20management/1024x384-size3x-1024x384_djx3oa.png"
              />
            </Paper>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
};

export default Home;
