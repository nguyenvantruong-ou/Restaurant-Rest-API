import React from 'react';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Unstable_Grid2';
import {
  CardActionArea,
  ImageList,
  ImageListItem,
  ImageListItemBar,
} from '@mui/material';
import { useState } from 'react';
import { useEffect } from 'react';
import FormatPrice from '../../FormatPrice';
import axios from 'axios';
import { CheckBox } from '@mui/icons-material';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'justify',
  color: theme.palette.text.secondary,
  height: 485,
  margin: theme.spacing(2, 0),
}));

const ListDish = () => {
  const [menu, setMenu] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-menu-dish?sort=default',
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setMenu(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <>
      {menu.map((item) => (
        <div key={item.id}>
          <span>
            <img
              alt="icon"
              style={{ width: 30 }}
              src="https://res.cloudinary.com/dqifjhxxg/image/upload/v1663949531/restaurant%20management/leaft_sj8lbp.svg"
            />
          </span>
          <Typography
            sx={{
              textAlign: 'left',
              color: '#790000',
              fontSize: 20,
              textTransform: 'uppercase',
            }}
          >
            {item.menuName} -- <FormatPrice price={item.menuPrice} />$
          </Typography>
          <Grid container>
            <Grid item xs={8}>
              <Item>
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="470"
                    image={item.menuImage}
                    alt="green iguana"
                  />
                </CardActionArea>
              </Item>
            </Grid>
            <Grid item xs={4}>
              <Item>
                <ImageList cols={1} sx={{ width: 370, height: 470 }}>
                  {item.listDish.map((dish) => (
                    <ImageListItem key={dish.dishName}>
                      <img
                        src={`${dish.dishImage}?w=248&fit=crop&auto=format`}
                        srcSet={`${dish.dishImage}?w=248&fit=crop&auto=format&dpr=2 2x`}
                        alt={dish.name}
                        loading="lazy"
                      />
                      <ImageListItemBar title={dish.dishName} />
                    </ImageListItem>
                  ))}
                </ImageList>
              </Item>
            </Grid>
          </Grid>
        </div>
      ))}
    </>
  );
};

export default ListDish;
