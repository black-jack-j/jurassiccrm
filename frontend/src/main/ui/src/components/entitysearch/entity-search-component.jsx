import React from "react";
import {SearchComponent} from "../search/search-component";

export const EntitySearchComponent = ({onSearchChanged, results, onValueChange, value, isLoading, ...props}) => {

    return <SearchComponent value={value}
                            results={results}
                            onSearchChange={onSearchChanged}
                            isLoading={isLoading}
                            onResultSelectChange={onValueChange} {...props}/>
}