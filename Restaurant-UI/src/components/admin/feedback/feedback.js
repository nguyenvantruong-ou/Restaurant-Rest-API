import './feedback.css';
import Error403 from '../../ErrorPages/Error403';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import FormatDate from '../../FormatDate';
import Moment from 'react-moment';
import { ChatBox } from 'react-chatbox-component';

const FeedbackAdmin = () => {
  const role = localStorage.getItem('role');
  const [feedbackGeneral, setFeedbackGeneral] = useState([]);
  const [currentFeedback, setCurrentFeedback] = useState(null);
  const [listFeedback, setListFeedback] = useState([]);
  const [userID, setUserID] = useState(0);

  const handleUserClick = (feedback) => {
    setCurrentFeedback(feedback);
    setUserID(feedback.user_id);
  };
  useEffect(() => {
    let configFeebackGeneral = {
      method: 'get',
      url: 'http://localhost:8989/api/admin/get-list-feedback-general',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(configFeebackGeneral)
      .then(function (response) {
        setFeedbackGeneral(response.data.data);
      })
      .catch(function (error) {
        toast.error(error.message);
      });

    var configFeedbackByUser = {
      method: 'get',
      url: `http://localhost:8989/api/admin/get-list-feedback-detail?user-id=${userID}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(configFeedbackByUser)
      .then(function (response) {
        setListFeedback(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [currentFeedback]);
  if (role === 'ADMIN') {
    return (
      <>
        <div className="title">Phản Hồi Từ Khách Hàng</div>
        <div className="box-feedback">
          <div className="box-left">
            {feedbackGeneral.map((fb) => (
              <div className="user-chat" onClick={() => handleUserClick(fb)}>
                <div>
                  <img src={fb.avatar}></img>
                </div>
                <span className="user-name">
                  {fb.name}
                  {fb.totalUnreadFeedback > 0 ? (
                    <span className="unread">{fb.totalUnreadFeedback}</span>
                  ) : (
                    ''
                  )}
                  <p className="last-feedback">{fb.lastFeedback}</p>
                </span>
              </div>
            ))}
          </div>
          <div className="box-right">
            <div className="header">
              {currentFeedback !== null ? (
                <span>
                  {currentFeedback.name} ({currentFeedback.username})
                </span>
              ) : (
                ''
              )}
            </div>
            <div className="boxchat-content">
              {listFeedback.map((item) => (
                <>
                  <div className="message">
                    <img src={currentFeedback.avatar}></img>
                    <div className="text">
                      <div>{item.content}</div>
                      <div className="date">
                        {FormatDate.formatFullDate(item.feedCreatedDate)}
                        {' | '}
                        <Moment fromNow>{item.feedCreatedDate}</Moment>
                      </div>
                    </div>
                  </div>
                </>
              ))}
            </div>
            <div className="boxchat-footer">
              <textarea rows={3} placeholder="phản hồi tại đây,,,,"></textarea>
            </div>
          </div>
        </div>
      </>
    );
  } else {
    return (
      <>
        <Error403 links={'/admin/login'} />
      </>
    );
  }
  return (
    <>
      <div className="title">Phản Hồi Từ Khách Hàng</div>
      <div className="box-feedback">
        <div className="box-left">
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
          <div className="user-chat">
            <img src="https://i.pinimg.com/564x/91/7f/1a/917f1ae7f2f72e5a975e207d1a9fed54.jpg"></img>
            <span className="user-name">Danh</span>
          </div>
        </div>
        <div className="box-right"></div>
      </div>
    </>
  );
};

export default FeedbackAdmin;
