import React, {useEffect, useState} from "react";
import {SearchComponent} from "../search/search-component";

const getValue = (entity, fieldSelector) => {
    if (typeof entity === 'undefined') {
        return undefined
    } else if (typeof fieldSelector === 'undefined') {
        return entity
    } else {
        return fieldSelector(entity)
    }
}

export const EntitySearchComponent = ({search, onValueChange, entityFieldSelector, valueFieldSelector, value=undefined, ...props}) => {

    const [isLoading, setIsLoading] = useState(false)
    const [results, setResults] = useState([])
    const [selected, setSelected] = useState(value)

    const displayValue = getValue(selected, entityFieldSelector)

    const onSearchChanged = (e, {value}) => {
        setIsLoading(true)
        setSelected(value)

        Promise.resolve(search(value)).then(results => {
            console.log(results)
            setIsLoading(false)
            setResults(results)
        }).catch(console.error)
    }

    const onResultSelectChanged = (e, {result}) => {
        setSelected(result)
    }

    useEffect(() => {
        onValueChange(getValue(selected, valueFieldSelector))
    }, [selected])

    return <SearchComponent value={displayValue}
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