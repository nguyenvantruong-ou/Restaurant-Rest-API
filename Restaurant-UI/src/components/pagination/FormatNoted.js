const FormatNoted = ({ note }) => {
  const myArr = note.split(';');
  let result = [];
  for (let i in myArr) {
    result.push(<div key={i}>{myArr[i]}</div>);
  }
  return result;
};

export default FormatNoted;
