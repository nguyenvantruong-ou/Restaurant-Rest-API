import React, { useEffect, useState } from 'react';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Unstable_Grid2';
import { Link } from 'react-router-dom';
import { CardActionArea, CardActions } from '@mui/material';
import axios from 'axios';
import { Button } from 'react-bootstrap';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'justify',
  color: theme.palette.text.secondary,
  height: 316,
  margin: theme.spacing(2, 0),
}));

const LobbyList = () => {
  const [lobbies, setLobbies] = useState([]);
  useEffect(() => {
    let config = {
      method: 'get',
      url: 'http://localhost:8989/api/client/get-list-lobby?size=5&page=1&kw=',
    };

    axios(config)
      .then(function (response) {
        const res = response.data;
        setLobbies(res.data.listLobby);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <>
      {lobbies.map((lobby, index) =>
        index % 2 === 0 ? (
          <Grid container key={lobby.id}>
            <Grid item xs={6}>
              <Item>
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="300"
                    image={lobby.lobImage}
                    alt="green iguana"
                  />
                </CardActionArea>
              </Item>
            </Grid>
            <Grid item xs={6}>
              <Item>
                <CardContent>
                  <Typography
                    color="#7e7786"
                    gutterBottom
                    variant="h5"
                    component="div"
                  >
                    {lobby.lobName}
                  </Typography>
                  <Typography variant="body1" color="text.secondary">
                    {lobby.lobDescription}
                  </Typography>
                </CardContent>
                <CardActions>
                  <Button className="btn-detail" variant="outline-primary">
                    <Link
                      className="link"
                      style={{ textDecoration: 'none' }}
                      to={`details/${lobby.id}`}
                    >
                      Xem chi tiết
                    </Link>
                  </Button>
                </CardActions>
              </Item>
            </Grid>
          </Grid>
        ) : (
          <Grid container key={lobby.id}>
            <Grid item xs={6}>
              <Item>
                <CardContent>
                  <Typography gutterBottom variant="h5" component="div">
                    {lobby.lobName}
                  </Typography>
                  <Typography variant="body1" color="text.secondary">
                    {lobby.lobDescription}
                  </Typography>
                </CardContent>
                <CardActions>
                  <Button className="btn-detail" variant="outline-primary">
                    <Link
                      className="link"
                      style={{ textDecoration: 'none', visited: '#fff' }}
                      to={`details/${lobby.id}`}
                    >
                      Xem chi tiết
                    </Link>
                  </Button>
                </CardActions>
              </Item>
            </Grid>
            <Grid item xs={6}>
              <Item>
                <CardActionArea>
                  <CardMedia
                    component="img"
                    height="300"
                    image={lobby.lobImage}
                    alt="green iguana"
                  />
                </CardActionArea>
              </Item>
            </Grid>
          </Grid>
        )
      )}
    </>
  );
};

export default LobbyList;
