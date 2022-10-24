const Banner = (props) => {
    return(
        <img 
            height={300}
            className="d-block w-100"
            src={props.imgURL} 
            alt="Banner"
        />
    )
}

export default Banner;