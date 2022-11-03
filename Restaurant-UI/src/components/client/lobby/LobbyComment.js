import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';
import Moment from 'react-moment';
import StarRatings from 'react-star-ratings';
import { toast } from 'react-toastify';
import './comment-lobby.css';

const LobbyComment = (props) => {
  const [isComment, setIsComment] = useState(false);
  const [cmtSuccess, setCmtSuccess] = useState(false);
  const [cmt, setCmt] = useState();

  function radioValue() {
    let numstars = 5;
    let ele = document.getElementsByName('radio-value');

    for (let i = 0; i < ele.length; i += 1) {
      if (ele[i].checked) numstars = ele[i].value;
    }

    return numstars;
  }
  const SentCommentClick = async () => {
    const content = document.getElementById('cmt-content');
    const numStars = radioValue();
    if (content.value !== '') {
      const data = {
        content: content.value,
        star: numStars,
        userID: localStorage.getItem('userID'),
        lobID: props.lobbyID,
        isIncognito: true,
      };

      var config = {
        method: 'post',
        url: 'http://localhost:8989/api/client/add-comment',
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          'Content-Type': 'application/json',
        },
        data: data,
      };

      axios(config)
        .then(function (response) {
          setCmtSuccess(true);
          toast.success(response.data.message, {
            position: toast.POSITION.TOP_RIGHT,
          });
        })
        .catch(function (error) {
          toast.error(error.message, {
            position: toast.POSITION.TOP_RIGHT,
          });
        });
    } else {
      toast.error('Vui lòng nhập bình luận');
    }
  };

  useEffect(() => {
    let config = {
      method: 'get',
      url: `http://localhost:8989/api/client/get-list-comment?lob_id=${props.lobbyID}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };

    axios(config)
      .then(function (response) {
        setCmt(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    var config3 = {
      method: 'post',
      url: 'http://localhost:8989/api/client/check-permission-comment',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json',
      },
      data: {
        userID: localStorage.getItem('userID'),
        lobID: props.lobbyID,
      },
    };
    if (localStorage.getItem('role') === 'USER') {
      axios(config3)
        .then(function (response) {
          setIsComment(response.data.data);
        })
        .catch(function (error) {
          toast.error(error.message, {
            position: toast.POSITION.TOP_RIGHT,
          });
        });
    }
  }, [cmtSuccess]);
  return (
    <>
      {cmt !== undefined ? (
        <div key={cmt.id}>
          <div className="cmt-title">Đánh giá sảnh tiệc</div>
          <div className="cmt-box-header">
            {/* <div>Tổng bình luận {cmt.amountTotalComment}</div> */}
            <div className="total-star">
              <span className="avg-star">{cmt.avgStar}</span>
              <span>trên 5</span>
              <div className="total-icon-star">
                <StarRatings
                  rating={cmt.avgStar}
                  starDimension="25px"
                  starSpacing="0px"
                  starRatedColor="#d00927"
                />
              </div>
            </div>
            <div className="overview-filter">5 Sao ({cmt.amount5Star})</div>
            <div className="overview-filter">4 Sao ({cmt.amount4Star})</div>
            <div className="overview-filter">3 Sao ({cmt.amount3Star})</div>
            <div className="overview-filter">2 Sao ({cmt.amount2Star})</div>
            <div className="overview-filter">1 Sao ({cmt.amount1Star})</div>
          </div>
          {isComment ? (
            <div className="box-write-cmt">
              <textarea id="cmt-content" maxLength={250}></textarea>
              <Button variant="danger" onClick={SentCommentClick}>
                Gửi bình luận
              </Button>
              <label for="radio-value">
                Rất Tệ
                <input type="radio" value={1} name="radio-value"></input>
              </label>
              <label for="radio-value">
                Tệ
                <input type="radio" value={2} name="radio-value"></input>
              </label>
              <label for="radio-value">
                Bình thường
                <input type="radio" value={3} name="radio-value"></input>
              </label>
              <label for="radio-value">
                Tốt
                <input type="radio" value={4} name="radio-value"></input>
              </label>
              <label for="radio-value">
                Rất tốt
                <input type="radio" value={5} name="radio-value"></input>
              </label>
            </div>
          ) : (
            ''
          )}
          {cmt.listComment.map((cmt) => {
            return (
              <div className="cmt-user" key={cmt.id}>
                <div className="box-image">
                  <img className="image" src={cmt.userImage} alt="anhr"></img>
                </div>
                <div className="cmt-content">
                  <div>{cmt.name}</div>
                  <div className="created-date">
                    <Moment fromNow>{cmt.createdDay}</Moment>
                    {' | '}
                    {new Date(cmt.createdDay).toLocaleString('vi-VI')}
                  </div>
                  <div className="star-area">
                    <StarRatings
                      rating={cmt.cmtStar}
                      starDimension="15px"
                      starSpacing="0px"
                      starRatedColor="#ff0000"
                    />
                  </div>
                  <div>{cmt.cmtContent}</div>
                </div>
              </div>
            );
          })}
        </div>
      ) : (
        ''
      )}
    </>
  );
};

export default LobbyComment;
