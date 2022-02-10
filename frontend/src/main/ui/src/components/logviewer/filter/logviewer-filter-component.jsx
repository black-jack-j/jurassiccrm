import {EntitySearchComponent} from "../../entitysearch/entity-search-component";
import React, {useContext, useState} from "react";
import ApiContext from "../../../api";
import _ from 'lodash';
import './logviewer-filter.css'

const filterSearch = input => {
    const re = new RegExp(_.escapeRegExp(input), 'i')
    return ({username}) => re.test(username)
}

const userToSearchResult = ({username}) => ({title: username, value: username})

export const LogViewerUsernameFilter = ({filter, ...props}) => {

    const [users, setUsers] = useState([])
    const [isLoading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const search = value => {
        setLoading(true)
        filter(undefined)
        return API.user.getUsers()
            .then(users => users.filter(filterSearch(value)))
            .then(setUsers)
            .then(() => setLoading(false))
            .catch(console.error);
    }

    const searchResults = users.map(userToSearchResult)

    const handleValueChange = ({value}) => {
        filter(value)
    }

    return <EntitySearchComponent className={'logviewer__filter'}
                                  results={searchResults}
                                  isLoading={isLoading}
                                  onSearchChanged={search} onValueChange={handleValueChange} {...props}/>

}