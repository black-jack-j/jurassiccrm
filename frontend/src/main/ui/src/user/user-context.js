import React, {useContext, useEffect, useState} from 'react'
import ApiContext from "../api";
import {User} from "./user";

const UserContext = React.createContext(null)

export const UserProvider = UserContext.Provider

export default UserContext

export const withCurrentUser = Component => () => {

    const [user, setUser] = useState(null)

    const API = useContext(ApiContext)

    const reload = () => {
        API.user.getCurrentUser().then(userTO => setUser(new User(userTO)))
    }

    useEffect(() => {
        reload()
    }, [])

    return (
        <UserProvider value={{user, reload}}>
            <Component />
        </UserProvider>
    )

}