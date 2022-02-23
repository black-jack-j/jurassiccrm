import {UserInfo} from "./userinfo";
import React, {Suspense, useContext, useEffect, useState} from "react";
import ApiContext from "../../api";
import {useAsyncResource} from "use-async-resource";
import {Avatar} from "../avatar/avatar";

export const UserInfoContainer = props => {

    const API = useContext(ApiContext)

    const {
        id
    } = props

    const [userReader] = useAsyncResource(API.user.getUserById.bind(API.user), {userId: id})

    return (
        <Suspense fallback={'Loading...'}>
            <Avatar src={`/api/user/${id}/icon`}/>
            <UserInfo userReader={userReader}/>
        </Suspense>
    )
}