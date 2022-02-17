import {useUsersSimple} from "../../user/user";
import {UsersViewer} from "./users-viewer";
import React from "react";

const getAvatarSrc = ({id}) => `/api/user/${id}/icon`

export const UsersViewerContainer = props => {

    const {
        onSelect
    } = props

    const [usersRequest, refresh] = useUsersSimple()

    const users = usersRequest.users.map(user => ({
        ...user,
        avatarSrc: getAvatarSrc(user)
    }))

    return <UsersViewer users={users} onSelect={onSelect}/>

}