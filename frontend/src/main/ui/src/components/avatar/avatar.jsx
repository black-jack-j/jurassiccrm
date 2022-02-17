import React from "react";
import './avatar.css'
import {Image} from "../image/image";

export const Avatar = props => {

    const {
        src,
        className = '',
        size = 'large',
        onClick
    } = props


    return (
        <Image
            onClick={onClick}
            className={`avatar ${className}`}
            circular size={size}
            src={src}
            alt={'avatar'}
        />
    )
}