import { Container, Grid, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import FormatPrice from '../../FormatPrice';
import Banner from '../Banner';
import axios from 'axios';
import LobbyComment from './LobbyComment';
import ThumbnailSlider from '../thumbnail-slider/ThumbnailSlider';

const imageUrl =
  'https://res.cloudinary.com/dqifjhxxg/image/upload/v1663856586/restaurant%20management/banner/_mg_9983_v3yqzw.jpg';

const LobbyDetail = () => {
  const [lobby, setLobby] = useState({});
  const [coef, setCoef] = useState();
  let { id } = useParams();
  useEffect(() => {
    let config = {
      method: 'get',
      url: `http://localhost:8989/api/client/get-lobby-by-id?id=${id}`,
      headers: {},
    };
    let config2 = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-coefficient',
      headers: {},
    };

    axios(config)
      .then(function (response) {
        setLobby(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
    axios(config2)
      .then(function (response) {
        setCoef(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  return (
    <>
      <Banner imgURL={imageUrl} />
      <Container>
        <Typography
          sx={{
            fontSize: 40,
            textAlign: 'left',
            marginTop: 3,
            color: '#c47135',
            textTransform: 'uppercase',
          }}
        >
          {lobby.lobName}
        </Typography>
        <Typography
          sx={{
            fontSize: 16,
            textAlign: 'justify',
            marginTop: 3,
            marginBottom: 3,
            textIndent: 30,
          }}
        >
          {lobby.lobDescription}
        </Typography>
        <Grid container spacing={12}>
          <Grid item xs={6}>
            <img
              src={lobby.lobImage}
              style={{
                width: 620,
                height: 350,
                marginBottom: 20,
              }}
              alt={lobby.lobName}
            />
          </Grid>
          <Grid item xs={6}>
            <div className="title-price">Chi tiết giá thuê sảnh</div>
            {lobby.lobPrice > 0 && coef !== undefined ? (
              <table className="price-detail">
                <thead>
                  <tr>
                    <th></th>
                    <th>Sáng</th>
                    <th>Trưa</th>
                    <th>Tối</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th>Ngày thường</th>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[0].coefValue || 0}
                      />
                    </td>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[1].coefValue || 0}
                      />
                    </td>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[2].coefValue || 0}
                      />
                    </td>
                  </tr>
                  <tr>
                    <th>Cuối tuần</th>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[3].coefValue || 0}
                      />
                    </td>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[4].coefValue || 0}
                      />
                    </td>
                    <td>
                      <FormatPrice
                        price={lobby.lobPrice * coef[5].coefValue || 0}
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            ) : (
              ''
            )}
          </Grid>
        </Grid>
        <div className="promotion" style={{ marginBottom: 20 }}>
          <p>
            <b>
              *** [KHUYẾN MÃI HOT]: ƯU ĐÃI 50% DÀNH CHO 10 CẶP ĐÔI ĐẦU TIÊN TRẢI
              NGHIỆM
            </b>{' '}
            chương trình Cưới theo cách bạn yêu.{' '}
          </p>
          <p style={{ marginTop: -10 }}>
            <b>
              ***[NHÂN ĐÔI ƯU ĐÃI KHI TỔ CHỨC ĐÁM CƯỚI ĐỘC NHẤT TẠI{' '}
              <span style={{ color: '#c47135' }}>THÀNH VĂN</span>]
            </b>
            Tại Thành Văn <b>BAO NHIÊU BÀN TIỆC BẤY NHIÊU TIỀN</b>
            không phát sinh thêm chi phí. Chỉ cần đặt tiệc tại Thành Văn bạn sẽ
            nhận ngay các <b>ƯU ĐÃI</b> sau:
          </p>
          <ul style={{ marginLeft: 50 }}>
            <li>
              Giảm giá trực tiếp <b>10% giá trị tiệc</b>
            </li>
            <li>
              <b>Tặng thức uống</b> (bia, nước ngọt, nước suối) suốt tiệc
            </li>
            <li>
              Tặng trọn gói <b>Nghi thức lễ và Trang trí tiêu chuẩn</b> trị giá
              38.000.000đ
            </li>
            <li>
              Tặng <b>01 đến 02 bàn tiệc</b>
            </li>
            <li>
              Tặng toàn bộ <b>chi phí phục vụ</b> trong tiệc
            </li>
            <li>
              Tặng <b>màn hình led</b> xuyên suốt tiệc và <b>bộ thiệp cưới</b>
            </li>
            <li>
              <b>Tặng quà</b> cho cô dâu - chú rể và <b>xố số may mắn</b> cho
              khách mời
            </li>
            <li>
              <b>Giảm 100.000đ/bàn</b> cho lần đặt tiếp theo và nhiều ưu đãi
              cưới khác
            </li>
            <li>
              <b>Giữ nguyên giá tiệc và khuyến mãi</b> cho đến ngày tiệc ngay
              khi đặt cọc
            </li>
          </ul>
        </div>
        {lobby.listImage !== undefined ? (
          lobby.listImage.length > 0 ? (
            <>
              <Typography
                sx={{
                  fontSize: 30,
                  textAlign: 'center',
                  marginTop: 5,
                  color: '#c47135',
                  textTransform: 'uppercase',
                }}
              >
                Một số ảnh chi tiết về sảnh
              </Typography>
              <div style={{ marginBottom: '150px' }}>
                <ThumbnailSlider listImages={lobby.listImage} />
              </div>
            </>
          ) : (
            ''
          )
        ) : (
          ''
        )}

        <LobbyComment lobbyID={id} />
      </Container>
    </>
  );
};

export default LobbyDetail;
