import {FormikSearchComponent} from "./formik-search-component";
import _ from "lodash";
import React, {useContext} from "react";
import ApiContext from "../../api";
import {FindAllByRolesAnyRolesEnum} from "../../generatedclient/apis";

const fieldMapper = entity => `${entity.firstName} ${entity.lastName}`
const valueMapper = entity => entity.id

const isStartsWithDog = value => {
    const stringVal = value.toString()
    return stringVal.length > 0 && stringVal.indexOf('@') === 0
}

const searchByNamePredicateBuilder = re => ({firstName, lastName}) => re.test(firstName) || re.test(lastName)
const searchByUsernamePredicateBuilder = re => ({username}) => re.test(username)

const toResultsMapper = ({username, ...other}) => ({
    title: fieldMapper({...other}),
    description: username,
    username,
    ...other
})

const searchByNameOrUsernameBuilder = API => async value => {
    const possibleAssignees = await API.user.findAllByRolesAny({roles: [
            FindAllByRolesAnyRolesEnum.ThemeZoneProjectWriter,
            FindAllByRolesAnyRolesEnum.DocumentWriter,
            FindAllByRolesAnyRolesEnum.Admin
        ]})
    let filteredAssignees
    if (isStartsWithDog(value)) {
        const re = new RegExp(_.escapeRegExp(value.substring(1)), 'i')
        filteredAssignees = _.filter(possibleAssignees, searchByUsernamePredicateBuilder(re))
    } else {
        const re = new RegExp(_.escapeRegExp(value), 'i')
        filteredAssignees = _.filter(possibleAssignees, searchByNamePredicateBuilder(re))
    }
    return filteredAssignees.map(toResultsMapper)
}

export const FormikUserSearchContainer = ({fieldName, ...props}) => {

    const API = useContext(ApiContext)

    return <FormikSearchComponent fieldName={fieldName}
                                  entityFieldSelector={fieldMapper}
                                  valueFieldSelector={valueMapper}
                                  search={searchByNameOrUsernameBuilder(API)}
                                  {...props}/>
}