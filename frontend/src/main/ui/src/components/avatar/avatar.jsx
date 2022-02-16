import React from "react";
import './avatar.css'

export const Avatar = props => {

    const {
        avatarSrc,
        className = ''
    } = props

    return <img className={`avatar ${className}`} src={avatarSrc} alt={'avatar'} />

}