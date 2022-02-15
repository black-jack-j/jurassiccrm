import React from "react";
import {ReactComponent as UserSVG} from "./user-icon.svg";
import './user-icon.css'
import '../jurassic-icon.css'
import {JurassicIcon} from "../jurassic-icon";

export const UserIcon = props => {

    const {
        className = '',
        ...other
    } = props

    return (
        <JurassicIcon className={`${className} jurassic_icon-user`} {...other}>
            <UserSVG/>
        </JurassicIcon>
    )

}