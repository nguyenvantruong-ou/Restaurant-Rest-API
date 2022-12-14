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
            <div className="title-price">Chi ti???t gi?? thu?? s???nh</div>
            {lobby.lobPrice > 0 && coef !== undefined ? (
              <table className="price-detail">
                <thead>
                  <tr>
                    <th></th>
                    <th>S??ng</th>
                    <th>Tr??a</th>
                    <th>T???i</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th>Ng??y th?????ng</th>
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
                    <th>Cu???i tu???n</th>
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
              *** [KHUY???N M??I HOT]: ??U ????I 50% D??NH CHO 10 C???P ????I ?????U TI??N TR???I
              NGHI???M
            </b>{' '}
            ch????ng tr??nh C?????i theo c??ch b???n y??u.{' '}
          </p>
          <p style={{ marginTop: -10 }}>
            <b>
              ***[NH??N ????I ??U ????I KHI T??? CH???C ????M C?????I ?????C NH???T T???I{' '}
              <span style={{ color: '#c47135' }}>TH??NH V??N</span>]
            </b>
            T???i Th??nh V??n <b>BAO NHI??U B??N TI???C B???Y NHI??U TI???N</b>
            kh??ng ph??t sinh th??m chi ph??. Ch??? c???n ?????t ti???c t???i Th??nh V??n b???n s???
            nh???n ngay c??c <b>??U ????I</b> sau:
          </p>
          <ul style={{ marginLeft: 50 }}>
            <li>
              Gi???m gi?? tr???c ti???p <b>10% gi?? tr??? ti???c</b>
            </li>
            <li>
              <b>T???ng th???c u???ng</b> (bia, n?????c ng???t, n?????c su???i) su???t ti???c
            </li>
            <li>
              T???ng tr???n g??i <b>Nghi th???c l??? v?? Trang tr?? ti??u chu???n</b> tr??? gi??
              38.000.000??
            </li>
            <li>
              T???ng <b>01 ?????n 02 b??n ti???c</b>
            </li>
            <li>
              T???ng to??n b??? <b>chi ph?? ph???c v???</b> trong ti???c
            </li>
            <li>
              T???ng <b>m??n h??nh led</b> xuy??n su???t ti???c v?? <b>b??? thi???p c?????i</b>
            </li>
            <li>
              <b>T???ng qu??</b> cho c?? d??u - ch?? r??? v?? <b>x??? s??? may m???n</b> cho
              kh??ch m???i
            </li>
            <li>
              <b>Gi???m 100.000??/b??n</b> cho l???n ?????t ti???p theo v?? nhi???u ??u ????i
              c?????i kh??c
            </li>
            <li>
              <b>Gi??? nguy??n gi?? ti???c v?? khuy???n m??i</b> cho ?????n ng??y ti???c ngay
              khi ?????t c???c
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
                M???t s??? ???nh chi ti???t v??? s???nh
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
