import React, {useContext, useState} from 'react'
import {FormikEntitySearchComponent} from "../usersearch/formik-entity-search-component";
import ApiContext from "../../api";
import _ from 'lodash'

const searchByNamePredicateBuilder = re => ({name}) => re.test(name)

const researchToSearchResult = ({name, id, ...props}) => ({
    title: name,
    id,
    ...props
})

const filterBuilder = value => {
    const re = new RegExp(_.escapeRegExp(value), 'i')
    return researches => _.filter(researches, searchByNamePredicateBuilder(re))
}

export const ResearchEntitySearchContainer = ({name,...props}) => {

    const [researches, setResearches] = useState([])
    const [isLoading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const search = input => {
        setLoading(true)
        const filter = filterBuilder(input)
        API.research.getAllResearches()
            .then(filter)
            .then(setResearches)
            .then(() => setLoading(false))
            .catch(console.error)
    }

    const searchResults = researches.map(researchToSearchResult)

    return (
        <FormikEntitySearchComponent onSearch={search}
                                     name={name}
                                     results={searchResults}
                                     isLoading={isLoading}
                                     {...props}/>
    )
}