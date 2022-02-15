import {useContext, useEffect, useState} from "react";
import ApiContext from "../api";

const initialState = {id: null, name: '', description: '', users: [], roles: []}

export const useGroupById = groupId => {

    const API = useContext(ApiContext)

    const [group, setGroup] = useState({group: {id: groupId, ...initialState}, state: 'loading'})

    useEffect(() => {
        API.group.getGroup({id: groupId})
            .then(group => {
                setGroup({group, state: 'loaded'})
            })
            .catch(error => {
                setGroup({group: {id: groupId, ...initialState}, state: 'error'})
            })
    }, [])

    return group

}

export const useGroups = () => {

    const API = useContext(ApiContext)

    const [groups, setGroups] = useState([])

    const refresh = () => API.group.getAllGroups().then(setGroups).catch(console.error)

    useEffect(() => {
        refresh()
    }, [])

    return [groups, refresh]

}