import React from "react";
import './avatar.css'

export const Avatar = props => {

    const {
        avatarSrc,
        className = '',
        size = 'large',
        circular = false
    } = props

    const imageSize = `image-size_${size}`

    const circularName = circular ? `image-circular` : ''

    return <img className={`avatar ${className} ${imageSize} ${circular}`} src={avatarSrc} alt={'avatar'} />

}