import {useContext, useEffect, useState} from "react";
import ApiContext from "../../api";

export const useRoles = () => {

    const [result, setResult] = useState({state: 'loading', value: [], error: null})

    const API = useContext(ApiContext)

    const refresh = () => API.role.getAllRoles()
        .then(roles => setResult({state: 'loaded', value: roles, error: null}))
        .catch(error => setResult({state: 'error', value: [], error}))

    useEffect(() => {
        refresh()
    }, [])

    return [result, refresh]

}