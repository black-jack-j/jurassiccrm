import React, {useState} from "react";
import {SearchComponent} from "../search/search-component";

export const EntitySearchComponent = ({search, onValueChange, entityFieldSelector, valueFieldSelector, value='', ...props}) => {

    const [isLoading, setIsLoading] = useState(false)
    const [results, setResults] = useState([])
    const [val, setSearchFieldValue] = useState(value)

    const onSearchChanged = (e, {value}) => {
        setIsLoading(true)
        setSearchFieldValue(value)
        onValueChange({})

        Promise.resolve(search(value)).then(results => {
            console.log(results)
            setIsLoading(false)
            setResults(results)
        }).catch(console.error)
    }

    const onResultSelectChanged = (e, {result}) => {
        if (typeof entityFieldSelector === 'undefined') {
            setSearchFieldValue(result)
        } else {
            setSearchFieldValue(entityFieldSelector(result))
        }
        if (typeof valueFieldSelector === 'undefined') {
            onValueChange(result)
        } else {
            onValueChange(valueFieldSelector(result))
        }
    }

    return <SearchComponent value={val}
                            results={results}
                            onSearchChange={onSearchChanged}
                            isLoading={isLoading}
                            onResultSelectChange={onResultSelectChanged} {...props}/>
}

export const EntitySearchHOC = (search, entityFieldSelector) => onValueChange => {
    return ({value='', ...props}) => {
        return <EntitySearchComponent search={search}
                                      entityFieldSelector={entityFieldSelector}
                                      onValueChange={onValueChange}
                                      value={value} {...props}/>
    }
}