import { Button, Grid, TextareaAutosize } from '@mui/material';
import React from 'react';
import { Container } from '@mui/system';
import Banner from '../Banner';
import TitlePage from '../TitlePage';
import {
  PermPhoneMsg,
  Favorite,
  CalendarMonth,
  MarkEmailRead,
} from '@mui/icons-material';
import axios from 'axios';
import { toast } from 'react-toastify';

const imageUrl =
  'https://res.cloudinary.com/dqifjhxxg/image/upload/v1663952078/restaurant%20management/banner/melisacenter-bg_eerzdd.jpg';
const Contact = () => {
  const sentFeedbackHandle = () => {
    let feedbackContent = document.getElementById('feedback-content');
    let role = localStorage.getItem('role');
    let config;
    if (role !== 'USER') {
      toast.error('vui lòng đăng nhập với tài khoản role USER', {
        position: toast.POSITION.TOP_RIGHT,
      });
    } else {
      if (feedbackContent.value !== '') {
        config = {
          method: 'post',
          url: 'http://localhost:8989/api/client/create-new-feedback',
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
          data: {
            content: feedbackContent.value,
            userID: localStorage.getItem('userID'),
          },
        };
        axios(config)
          .then(function (response) {
            feedbackContent.value = null;
            toast.success(response.data.message);
          })
          .catch(function (error) {
            toast.error(error.message);
          });
      } else {
        toast.error('vui lòng nhập nội dung', {
          position: toast.POSITION.TOP_RIGHT,
        });
      }
    }
  };
  return (
    <>
      <Banner imgURL={imageUrl} />
      <div style={{ textAlign: 'center', marginTop: 30 }}>
        <PermPhoneMsg sx={{ color: '#dd9933', fontSize: 60 }} />
      </div>
      <Container>
        <TitlePage title="Liên hệ với chúng tôi" />
        <Grid container spacing={2} sx={{ marginTop: 1, marginBottom: 5 }}>
          <Grid
            item
            xs={7}
            sx={{
              backgroundColor: 'rgba(10,10,10,0.01)',
              marginLeft: 4,
              marginRight: 5,
            }}
          >
            <p className="contact">
              <i>
                <b> Thành Văn</b>
              </i>{' '}
              - Trung tâm tiệc cưới hội nghị sang trọng và tiện nghi bậc nhất
              khu vực Tây thành phố Hồ Chí Minh{' '}
              <i>
                <b>
                  tọa lạc tại 371 Nguyễn Kiệm, Phường 3 , Quận Gò Vấp, Thành phố
                  Hồ Chí Minh
                </b>
              </i>
            </p>
            <p className="contact">
              Khu vực trung tâm của các quận Tân Bình - Bình Tân - Tân Phú -
              Quận 6 - Quận 8 - Quận 11. Nằm giữa Đầm Sen - Aeon - Galaxy Tân
              Bình
            </p>
            <p className="contact">
              Là một trong số ít trung tâm tiệc cưới ở thành phố Hồ Chí Minh
              cung cấp loại sảnh tiệc đặc biệt -{' '}
              <i>
                <b>phục vụ đến 2.000 khách trong cùng một không gian</b>
              </i>
              . Điều này giúp Thành Văn trở thành sự lựa chọn hàng đầu cho các
              cặp đôi có số lượng khách mời khủng.
            </p>
            <p className="contact">
              Bên cạnh đó, việc{' '}
              <i>
                <b>
                  di chuyển giao thông rất thuận lợi với 4.000 m2 trong khuôn
                  viên dành cho bãi giữ xe ô tô và xe máy
                </b>
              </i>
            </p>
          </Grid>
          <Grid item xs={4} sx={{ backgroundColor: 'rgba(5, 5, 5, 0.04)' }}>
            <div className="contact">
              <Grid container>
                <Grid item xs={2}>
                  {' '}
                  <Favorite
                    sx={{
                      color: '#dd9933',
                      marginLeft: 1,
                      marginTop: 1,
                      fontSize: 40,
                    }}
                  />
                </Grid>
                <Grid item xs={10}>
                  <h4> Liên hệ Yêu cầu báo giá</h4>
                  <div>
                    <b>Hotline:</b> 0903995643
                  </div>
                  <div>
                    <b>Email:</b> thanhvan@ou.edu.vn
                  </div>
                </Grid>
              </Grid>
            </div>
            <div className="contact">
              <Grid container>
                <Grid item xs={2}>
                  {' '}
                  <CalendarMonth
                    sx={{
                      color: '#dd9933',
                      marginLeft: 1,
                      marginTop: 1,
                      fontSize: 40,
                    }}
                  />
                </Grid>
                <Grid item xs={10}>
                  <h4>Đặt lịch tham quan</h4>
                  <div>
                    <b>Số điện thoại:</b> 0903995643
                  </div>
                </Grid>
              </Grid>
            </div>
            <div className="contact">
              <Grid container>
                <Grid item xs={2}>
                  {' '}
                  <MarkEmailRead
                    sx={{
                      color: '#dd9933',
                      marginLeft: 1,
                      marginTop: 1,
                      fontSize: 40,
                    }}
                  />
                </Grid>
                <Grid item xs={10}>
                  <h4>Phản hồi - Đánh giá</h4>
                  <div>
                    <b>Hotline:</b> 0903995643
                  </div>
                  <div>
                    <b>Email:</b> thanhvan@ou.edu.vn
                  </div>
                </Grid>
              </Grid>
            </div>
          </Grid>
        </Grid>
        <Grid container style={{ marginBottom: 10 }}>
          <Grid item xs={7}>
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.9251197291956!2d106.67491891463938!3d10.817042392293937!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x317528e1f241211f%3A0xc9ef195798144b1f!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBN4bufIFRQLkhDTSAtIEPGoSBz4bufIDM!5e0!3m2!1svi!2s!4v1663859918389!5m2!1svi!2s"
              style={{ width: 650, height: 400, border: 0 }}
              allowFullScreen=""
              loading="lazy"
              title="map"
              referrerPolicy="no-referrer-when-downgrade"
            ></iframe>
          </Grid>
          <Grid item xs={5}>
            <TextareaAutosize
              maxLength={250}
              aria-label="empty textarea"
              placeholder="Hãy để lại phản hồi của bạn tại đây..."
              style={{ width: 500, height: 350, padding: 10 }}
              id="feedback-content"
            />
            <Button onClick={sentFeedbackHandle} variant="contained">
              Gửi
            </Button>
          </Grid>
        </Grid>
      </Container>
    </>
  );
};

export default Contact;
