const Banner = (props) => {
  return (
    <div className="banner">
      <img
        height={300}
        className="d-block w-100 zoom-in-out"
        src={props.imgURL}
        alt="Banner"
      />
    </div>
  );
};

export default Banner;
