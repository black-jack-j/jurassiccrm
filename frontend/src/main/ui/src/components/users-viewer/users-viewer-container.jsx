import {useUsersSimple} from "../../user/user";
import {UsersViewer} from "./users-viewer";
import React, {useContext} from "react";
import UserContext from "../../user/user-context";
import {useDispatch} from "react-redux";
import {open} from "../create-user-form-popup/create-user-form-popup-slice"

const getAvatarSrc = ({id}) => `/api/user/${id}/icon`

export const UsersViewerContainer = props => {

    const {
        onSelect
    } = props

    const [usersRequest, refresh] = useUsersSimple()

    const {user} = useContext(UserContext)

    const canAddUsers = user && user.canEditUsers()

    const dispatch = useDispatch()
    const onAdd = () => dispatch(open())

    const users = usersRequest.users.map(user => ({
        ...user,
        avatarSrc: getAvatarSrc(user)
    }))

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