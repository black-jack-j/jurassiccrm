import React, {useContext} from 'react'
import {FormikSearchComponent} from "../usersearch/formik-search-component";
import ApiContext from "../../api";
import _ from 'lodash'

const entityFieldMapper = entity => entity.name

const entityValueMapper = entity => entity.id

const searchByNamePredicateBuilder = re => ({name}) => re.test(name)

const resultsMapper = ({name, ...props}) => ({
    title: name,
    name,
    ...props
})

const searchBuilder = API => async value => {
    const results = await API.research.getAllResearches()
    const re = new RegExp(_.escapeRegExp(value), 'i')
    return _.filter(results, searchByNamePredicateBuilder(re)).map(resultsMapper)
}

export const ResearchSearchContainer = ({...props}) => {

    const API = useContext(ApiContext)

    return (
        <FormikSearchComponent search={searchBuilder(API)}
                               valueFieldSelector={entityValueMapper}
                               entityFieldSelector={entityFieldMapper}
                               {...props}/>
    )
}