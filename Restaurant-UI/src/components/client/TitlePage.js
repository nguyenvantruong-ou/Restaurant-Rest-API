import { Typography } from '@mui/material';

const TitlePage = (props) => {
  return (
    <div>
      <Typography
        variant="h4"
        sx={{
          textAlign: 'center',
          marginTop: 3,
          marginBottom: 3,
          color: '#790000',
          textTransform: 'uppercase',
        }}
      >
        <span>
          <img
            alt=""
            src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663949571/restaurant%20management/right-sep_oykcyx.svg"
            height={40}
          />
        </span>{' '}
        {props.title}{' '}
        <span>
          <img
            alt=""
            src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663949554/restaurant%20management/left-sep_yyyxd0.svg"
            height={40}
          />
        </span>
      </Typography>
    </div>
  );
};

export default TitlePage;
