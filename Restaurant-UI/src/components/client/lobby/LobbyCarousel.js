import Carousel from 'react-bootstrap/Carousel';

const LobbyCarousel = () => {
  return (
    <Carousel fade>
      <Carousel.Item>
        <img
          height={400}
          className="d-block w-100"
          src="https://www.burnabyhall.com/wp-content/uploads/static/slide-1.jpg"
          alt="First slide"
        />
        <Carousel.Caption>
          <h3>First slide label</h3>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </Carousel.Caption>
      </Carousel.Item>

      <Carousel.Item>
        <img
          height={400}
          className="d-block w-100"
          src="https://ashlandhillshotel.com/wp-content/uploads/2017/08/BG-wedding-banner-top.jpg"
          alt="Second slide"
        />
        <Carousel.Caption>
          <h3>Second slide label</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </Carousel.Caption>
      </Carousel.Item>

      <Carousel.Item>
        <img
          height={400}
          className="d-block w-100"
          src="https://www.1-host.sg/wp-content/uploads/2021/01/The-Riverhouse-Wedding-Banner.jpg"
          alt="Third slide"
        />
        <Carousel.Caption>
          <h3>Third slide label</h3>
          <p>
            Praesent commodo cursus magna, vel scelerisque nisl consectetur.
          </p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
};

export default LobbyCarousel;
