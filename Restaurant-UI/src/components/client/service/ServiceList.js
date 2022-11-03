import React from 'react';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Unstable_Grid2';
import { CardActionArea } from '@mui/material';
import { useState } from 'react';
import { useEffect } from 'react';
import FormatPrice from '../../FormatPrice';
import axios from 'axios';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'justify',
  color: theme.palette.text.secondary,
  height: 315,
  margin: theme.spacing(2, 0),
}));

const ServiceList = () => {
  const [services, setServices] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-service',
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setServices(res.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <>
      <Grid container>
        {services.map((service, index) => (
          <Grid key={index} item xs={6} display={'flex'}>
            <Grid item xs={6}>
              <Item>
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="300"
                    image={service.serImage}
                    alt="green iguana"
                  />
                </CardActionArea>
              </Item>
            </Grid>
            <Grid item xs={6}>
              <Item>
                <CardContent sx={{ textAlign: 'center' }}>
                  <Typography
                    gutterBottom
                    sx={{ fontSize: 24, color: '#790000' }}
                    component="div"
                  >
                    {service.serName}
                  </Typography>
                  <Typography sx={{ fontSize: 20 }} color="text.secondary">
                    {service.serDescription}
                  </Typography>
                  <Typography
                    sx={{
                      fontSize: 22,
                      marginTop: 1,
                      color: '#333',
                      fontStyle: 'italic',
                    }}
                  >
                    Giá: <FormatPrice price={service.serPrice} /> VNĐ
                  </Typography>
                </CardContent>
              </Item>
            </Grid>
          </Grid>
        ))}
      </Grid>
    </>
  );
};

export default ServiceList;
