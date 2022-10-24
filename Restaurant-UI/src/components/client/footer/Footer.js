import React from "react";
import './Footer.css'

const Footer = () => {
    return (
        <footer>
            <div className="footer-content">
                <h3>Wedding Restaurant Thành Văn</h3>
                <p>Địa chỉ: 371 Nguyễn Kiệm, phường 3, Gò Vấp, Hồ Chí Minh</p>
                <ul className="socials">
                    <li><a href="/"><i className="fa fa-facebook"></i></a></li>
                    <li><a href="/"><i className="fa fa-twitter"></i></a></li>
                    <li><a href="/"><i className="fa fa-google-plus"></i></a></li>
                    <li><a href="/"><i className="fa fa-youtube"></i></a></li>
                    <li><a href="/"><i className="fa fa-linkedin-square"></i></a></li>
                </ul>
                <div className="footer-bottom" style={{width:'100%'}}>
                </div>
                <p>Myteam &copy;2022 </p>
            </div>
        </footer>
    );
}

export default Footer