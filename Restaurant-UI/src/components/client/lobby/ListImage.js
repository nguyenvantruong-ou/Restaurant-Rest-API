import './list-image.css';
const ListImage = (props) => {
  console.log(props.images);
  for (let i = 0; i < props.images.length; i += 1) {}
  return (
    <>
      <div className="slider">
        <input type="radio" name="slide_switch" id="id1" defaultChecked />
        <label for="id1">
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
      </div>
    </>
  );
};

export default ListImage;
