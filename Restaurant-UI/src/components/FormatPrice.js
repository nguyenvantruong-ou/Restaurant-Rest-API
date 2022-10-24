const FormatPrice = (props) =>
  props.price.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');

export default FormatPrice;
