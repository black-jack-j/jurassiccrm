import React from "react";

export const UserInfo = props => {

    const {
        userReader
    } = props

    const {
        firstName,
        lastName
    } = userReader()

    return <div>{firstName} {lastName}</div>

}