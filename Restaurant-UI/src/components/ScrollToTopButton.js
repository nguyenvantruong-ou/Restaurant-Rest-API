import { IconButton } from "@mui/material";
import KeyboardDoubleArrowUpIcon from '@mui/icons-material/KeyboardDoubleArrowUp';
import React from "react";
import { useEffect, useState } from 'react';

const ScrollToTopButton = () => {
    const [ScrollToTopButton, setScrollToTopButton] = useState(false)

    useEffect(() => {
        window.addEventListener("scroll", () => {
            if (window.scrollY > 200) {
                setScrollToTopButton(true)
            } else {
                setScrollToTopButton(false)
            }
        })
    })

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        })
    }

    return (
        <>
            {ScrollToTopButton && (
                <IconButton color="inherit" size="large" component="label"
                    sx={{
                        bottom: 20,
                        right: 10,
                        width: 80,
                        height: 80,
                        position: "fixed"
                    }}
                    onClick={scrollUp}
                >
                    <KeyboardDoubleArrowUpIcon sx={{ fontSize: 70 }} />
                </IconButton>
            )}
        </>
    )
}

export default ScrollToTopButton;