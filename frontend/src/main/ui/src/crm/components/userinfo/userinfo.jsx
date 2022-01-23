import React from "react";

export const UserInfo = props => {

    const {
        firstName,
        lastName
    } = props

    return <div>{firstName} {lastName}</div>

}