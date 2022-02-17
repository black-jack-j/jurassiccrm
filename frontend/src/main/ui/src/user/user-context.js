import React, {useContext, useEffect, useState} from 'react'
import ApiContext from "../api";

const UserContext = React.createContext(null)

export const UserProvider = UserContext.Provider

export default UserContext

export const withCurrentUser = Component => () => {

    const [user, setUser] = useState(null)

    const API = useContext(ApiContext)

    const reload = () => {
        API.user.getCurrentUser().then(setUser)
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