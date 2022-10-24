import './thumbnail-slider.css';

const ThumbnailSlider = () => {
  return (
    <>
      <div class="slider">
        <input type="radio" name="slide_switch" id="id1" defaultChecked />
        <label for="id1">
          <img
            src="http://thecodeplayer.com/uploads/media/3yiC6Yq.jpg"
            width="100"
            alt="lobby"
          />
        </label>
        <img
          src="http://thecodeplayer.com/uploads/media/3yiC6Yq.jpg"
          alt="lobby"
        />

        <input type="radio" name="slide_switch" id="id2" />
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
        />
      </div>
    </>
  );
};

export default ThumbnailSlider;
