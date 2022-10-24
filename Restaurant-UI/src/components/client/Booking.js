import * as React from 'react';
import { Container } from '@mui/system';
import TitlePage from './TitlePage'

const Booking = () => {
    // const lobbies = [
    //     { 'id': 1, 'name': 'Luxury' },
    //     { 'id': 2, 'name': 'Rosy' },
    //     { 'id': 3, 'name': 'Golden' },
    //     { 'id': 4, 'name': 'Pinky' }
    // ]
    // const menus = [
    //     { 'id': 1, 'name': 'Thực đơn 1' },
    //     { 'id': 2, 'name': 'Thực đơn 2' },
    //     { 'id': 3, 'name': 'Thực đơn 3' },
    //     { 'id': 4, 'name': 'Thực đơn 4' }
    // ]
    // const [lobby, setLobby] = React.useState('');
    // const handleChange = (event) => {
    //     setLobby(event.target.value);
    // };
    return (
        <Container>
            <div className='border'>
                <div style={{ marginTop: 10 }}>
                    <TitlePage title='Đặt tiệc' />
                </div>
            </div>
        </Container >

    )
}

export default Booking;