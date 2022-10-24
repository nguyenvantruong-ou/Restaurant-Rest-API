import './feedback.css';
import Error403 from '../../ErrorPages/Error403';

const FeedbackAdmin = () => {
  const role = localStorage.getItem('role');
  if (role === 'ADMIN') {
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
  } else {
    return (
      <>
        <Error403 />
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
