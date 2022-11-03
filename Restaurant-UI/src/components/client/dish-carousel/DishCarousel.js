import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './dish-carousel.css';
import axios from 'axios';

const DishCarousel = () => {
  const [dishes, setDishes] = useState([]);
  var settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
  };
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-dish',
    };

    axios(config)
      .then(function (response) {
        setDishes(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <div className="dish-content">
      <Slider {...settings} autoplay autoplaySpeed={1800}>
        {dishes.map((dish) => {
          return (
            <div className="card" key={dish.id}>
              <div className="card-top">
                <img src={dish.dishImage} alt={dish.dishName} />
              </div>
            </div>
          );
        })}
      </Slider>
    </div>
  );
};

export default DishCarousel;
