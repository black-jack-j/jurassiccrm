import React from "react";

export * from './user/user-icon'

export const JurassicIcon = props => {

    const {
        className = '',
        circular,
        size,
        children
    } = props

    const iconSize = 'jurassic_icon-size_' + size

    return <div className={`${className} jurassic_icon ${circular ? 'jurassic_icon-circular' : ''} ${iconSize}`}>{children}</div>

}
