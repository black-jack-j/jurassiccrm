import {UserInfo} from "./userinfo";
import React, {useEffect, useState} from "react";
import {API} from "../../../api";

export const UserInfoContainer = props => {

    const {
        id
    } = props

    const [state, setState] = useState({userInfo: {}, isLoading: true})

    useEffect(() => {
        if (typeof id !== 'undefined') {
            setState({userInfo: {}, isLoading: true})
            API.user.getUserById({userId: id}).then(user => {
                console.log(user)
                setState({userInfo: {...user}, isLoading: false})
            })
        }
    }, [])

    if (typeof id === 'undefined') {
        return <div></div>
    }

    return <UserInfo {...state.userInfo}/>

}