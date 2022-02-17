import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../api";
import {UserProvider} from "./user-context";

export const useUsersSimple = () => {

    const [result, setResult] = useState({state: 'loading', users: [], error: null})

    const API = useContext(ApiContext)

    const refresh = () => API.user.getUsersSimple()
        .then(users => setResult({state: 'loaded', users, error: null}))
        .catch(error => setResult({state: 'error', users: [], error}))

    useEffect(() => {
        refresh()
    }, [])

    return [result, refresh]
}

export const useUser = userId => {

    const [result, setResult] = useState({state: 'loading', user: null, error: null})

    const API = useContext(ApiContext)

    const reload = () => {
        API.user.getUserById({userId}).then(user => {
            setResult({state: 'loaded', user, error: null})
        }).catch(error => {
            setResult({state: 'error', user: null, error})
        })
    }

    useEffect(() => {
        reload()
    }, [])

    return [result, reload]

}