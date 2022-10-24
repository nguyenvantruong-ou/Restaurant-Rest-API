import { Typography } from "@mui/material";
import React from "react";
import Banner from "../Banner";
import ListDish from "./ListDish";
import TitlePage from "../TitlePage";
import LocalDiningIcon from '@mui/icons-material/LocalDining';
import { Container } from "@mui/system";

const bannerURL = "https://res.cloudinary.com/dqifjhxxg/image/upload/v1661664399/restaurant%20management/banner/menu-header_unfbkj.jpg"

const Menu = () => {
    return (
        <>
            <Banner imgURL={bannerURL} />
            <Container>
                <div style={{ textAlign: 'center', marginTop: 20 }}>
                    <LocalDiningIcon sx={{ color: '#dd9933', fontSize: 60 }} />
                </div>
                <TitlePage title='ẩm thực thành văn' />
                <Typography variant="h6"
                    sx={
                        {
                            textAlign: 'center',
                            marginTop: 3,
                            marginBottom: 3,
                            color: '#555',

                        }
                    }
                >
                    Nếu không gian tiệc cưới sang trọng, đẳng cấp gây ấn tượng đầu tiên cho khách mời thì hương vị,
                    cách bày trí món ăn lại đem đến thành công quyết định cho một buổi tiệc hoàn hảo
                </Typography>
                <ListDish />
            </Container>
        </>
    );
}

export default Menu