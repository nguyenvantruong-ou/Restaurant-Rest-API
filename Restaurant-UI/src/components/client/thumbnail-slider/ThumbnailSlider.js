import './thumbnail-slider.css';

const ThumbnailSlider = ({ listImages }) => {
  let imglink, arrCopy;
  if (listImages.length > 0) {
    arrCopy = [...listImages];
    imglink = arrCopy.splice(0, 1);
  }
  return (
    <>
      <div className="slider">
        {listImages.length > 0 ? (
          <>
            <input type="radio" name="slide_switch" id={0} defaultChecked />
            <label for={0}>
              <img src={imglink} width="100" height={60} alt="lobby" />
            </label>
            <img src={imglink} width="100%" height={'100%'} alt="lobby" />
            {arrCopy.map((item, index) => (
              <>
                <input type="radio" name="slide_switch" id={index + 1} />
                <label for={index + 1}>
                  <img src={item} width="100" height={60} alt="lobby" />
                </label>
                <img src={item} width="100%" height={'100%'} alt="lobby" />
              </>
            ))}
          </>
        ) : (
          ''
        )}

        {/* <input type="radio" name="slide_switch" id="id2" />
        <label for="id2">
          <img
            src="http://thecodeplayer.com/uploads/media/40Ly3VB.jpg"
            width="100"
            alt="lobby"
          />
        </label>
        <img
          src="http://thecodeplayer.com/uploads/media/40Ly3VB.jpg"
          alt="lobby"
        />

        <input type="radio" name="slide_switch" id="id3" />
        <label for="id3">
          <img
            src="http://thecodeplayer.com/uploads/media/00kih8g.jpg"
            width="100"
            alt="lobby"
          />
        </label>
        <img
          src="http://thecodeplayer.com/uploads/media/00kih8g.jpg"
          alt="lobby"
        />

        <input type="radio" name="slide_switch" id="id4" />
        <label for="id4">
          <img
            src="http://thecodeplayer.com/uploads/media/2rT2vdx.jpg"
            width="100"
            alt="lobby"
          />
        </label>
        <img
          src="http://thecodeplayer.com/uploads/media/2rT2vdx.jpg"
          alt="lobby"
        />

        <input type="radio" name="slide_switch" id="id5" />
        <label for="id5">
          <img
            src="http://thecodeplayer.com/uploads/media/8k3N3EL.jpg"
            width="100"
            alt="lobby"
          />
        </label>
        <img
          src="http://thecodeplayer.com/uploads/media/8k3N3EL.jpg"
          alt="lobby"
        /> */}
      </div>
    </>
  );
};

export default ThumbnailSlider;
