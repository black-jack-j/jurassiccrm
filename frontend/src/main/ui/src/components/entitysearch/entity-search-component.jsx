import React from "react";
import {SearchComponent} from "../search/search-component";

export const EntitySearchComponent = ({onSearchChanged, results, onValueChange, value, isLoading, disabled,...props}) => {

    return <SearchComponent value={value}
                            results={results}
                            onSearchChange={onSearchChanged}
                            isLoading={isLoading}
                            disabled={disabled}
                            onResultSelectChange={onValueChange} {...props}/>
}