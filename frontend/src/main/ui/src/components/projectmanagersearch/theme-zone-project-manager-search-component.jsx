import React, {useContext, useState} from "react";
import {searchByNameOrUsernameBuilder} from "../utilities/search-utils";
import {FormikEntitySearchComponent} from "../usersearch/formik-entity-search-component";
import ApiContext from "../../api";
import {UserWithRolesTORolesEnum as Roles} from "../../generatedclient/models";

const managerToSearchResult = ({firstName, lastName, username, id}) => ({
    title: `${firstName} ${lastName}`,
    description: username,
    id
})

export const ThemeZoneProjectManagerSearchComponent = ({name,...props}) => {

    const [managers, setManagers] = useState([])
    const [isLoading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const searchManagers = input => {
        const filter = searchByNameOrUsernameBuilder(input)
        setLoading(true)
        API.user.findAllByRolesAny({roles:[Roles.ThemeZoneProjectWriter, Roles.Admin]})
            .then(filter)
            .then(setManagers)
            .then(() => setLoading(false))
            .catch(console.error)
    }

    const searchResults = managers.map(managerToSearchResult)

    return (
        <FormikEntitySearchComponent isLoading={isLoading}
                                     onSearch={searchManagers}
                                     name={name}
                                     results={searchResults}
                                     {...props}/>
    )

}