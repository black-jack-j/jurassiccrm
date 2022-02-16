import React from "react";
import './image.css'

export const Image = props => {

    const {
        src,
        alt,
        size = 'medium',
        className = '',
        circular
    } = props

    const sizeClassName = `image-size_${size}`
    const circularClassName = circular ? `image-circular` : ''
    const classNames = `image ${className} ${sizeClassName} ${circularClassName}`

    return <img className={classNames} alt={alt} src={src}/>

}