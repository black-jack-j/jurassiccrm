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
       ...props
    }) => {

    return (
        <Search input={{icon: 'search', iconPosition: 'right'}}
            loading={isLoading}
            onSearchChange={_.debounce(onSearchChange, 500, {leading: true})}
            onResultSelect={onResultSelectChange}
            results={results}
            value={value}
            {...props} />
    )

}