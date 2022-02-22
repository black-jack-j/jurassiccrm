import {UsersViewer} from "./users-viewer";
import React, {useContext, useEffect} from "react";
import UserContext from "../../user/user-context";
import {useDispatch, useSelector} from "react-redux";
import {open} from "../create-user-form-popup/create-user-form-popup-slice"
import {selectUsers, updateUsers} from "../../user/user-slice";
import ApiContext from "../../api";

const getAvatarSrc = ({id}) => `/api/user/${id}/icon`

export const UsersViewerContainer = props => {

    const {
        onSelect
    } = props

    const users = useSelector(selectUsers).map(user => ({
        ...user,
        avatarSrc: getAvatarSrc(user)
    }))

    const dispatch = useDispatch()

    const API = useContext(ApiContext)
    const refresh = () => API.user.getUsersSimple().then(users => {
        dispatch(updateUsers(users))
    }).catch(console.error)

    useEffect(() => {
        refresh()
    }, [])

    const {user} = useContext(UserContext)

    const canAddUsers = user && user.canEditUsers()

    const onAdd = () => dispatch(open())

    return (
        <UsersViewer
            users={users}
            onSelect={onSelect}
            canAdd={canAddUsers}
            onAdd={onAdd}
            refresh={refresh}
        />
    )

}