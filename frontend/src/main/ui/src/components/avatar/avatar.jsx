import React from "react";
import './avatar.css'
import {Image} from "../image/image";

export const Avatar = props => {

    const {
        src,
        className = '',
        size = 'large'
    } = props


    return (
        <Image
            className={`avatar ${className}`}
            circular size={size}
            src={src}
            alt={'avatar'}
        />
    )
}