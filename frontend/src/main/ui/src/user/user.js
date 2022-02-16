import {useContext, useEffect, useState} from "react";
import ApiContext from "../api";

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