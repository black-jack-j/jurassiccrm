import {EntitySearchComponent} from "../../entitysearch/entity-search-component";
import React, {useContext} from "react";
import ApiContext from "../../../api";
import _ from 'lodash';
import './logviewer-filter.css'

const fieldMapper = entity => entity.username
const valueMapper = entity => entity.username

const filterSearch = input => {
    const re = new RegExp(_.escapeRegExp(input), 'i')
    return entity => re.test(valueMapper(entity))
}

export const LogViewerUsernameFilter = ({filter, ...props}) => {

    const API = useContext(ApiContext)

    const search = value => {
        return API.user.getUsers()
            .then(users => users.filter(filterSearch(value)))
            .then(users => users.map(user => ({title: fieldMapper(user), ...user})));
    }

    return <EntitySearchComponent className={'logviewer__filter'}
                                  entityFieldSelector={fieldMapper}
                                  valueFieldSelector={valueMapper}
                                  search={search} onValueChange={filter} {...props}/>

}