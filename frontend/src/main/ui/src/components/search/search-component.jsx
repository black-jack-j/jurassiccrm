import {Search} from "semantic-ui-react";
import React from "react";
import _ from "lodash"

export const SearchComponent = (
    {
       isLoading,
       onSearchChange,
       onResultSelectChange,
       results,
       value,
        disabled,
       ...props
    }) => {

    const handleSearch = (e, {value}) => onSearchChange(value)
    const handleSelect = (e, {result}) => onResultSelectChange(result)

    return (
        <Search
            disabled={disabled}
            loading={isLoading}
            onSearchChange={_.debounce(handleSearch, 500, {leading: true})}
            onResultSelect={handleSelect}
            results={results}
            value={value}
            {...props} />
    )

}