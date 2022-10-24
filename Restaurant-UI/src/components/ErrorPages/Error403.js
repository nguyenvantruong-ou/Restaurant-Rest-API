import './error403.css';
import { Link } from 'react-router-dom';

const Error403 = () => {
  return (
    <div className="box-content-403">
      <div className="scene">
        <div className="overlay"></div>
        <div className="overlay"></div>
        <div className="overlay"></div>
        <div className="overlay"></div>
        <span className="bg-403">403</span>
        <div className="text">
          <span className="hero-text"></span>
          <span className="msg">
            can't let <span>you</span> in.
          </span>
          <span className="support">
            <span>unexpected?</span>
            <Link to={'/auth/login'}>Login</Link>
          </span>
        </div>
        <div className="lock"></div>
      </div>
    </div>
  );
};

export default Error403;
