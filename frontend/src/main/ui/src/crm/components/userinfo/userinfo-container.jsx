import {UserInfo} from "./userinfo";
import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../api";

export const UserInfoContainer = props => {

    const API = useContext(ApiContext)

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