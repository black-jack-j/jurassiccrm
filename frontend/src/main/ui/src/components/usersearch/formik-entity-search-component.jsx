import {useField} from "formik";
import React from "react";
import {EntitySearchComponent} from "../entitysearch/entity-search-component";

export const FormikEntitySearchComponent = ({results, name, onSearch, isLoading, disabled,...props}) => {

    const [value, meta, helpers] = useField(name)

    const onValueChange = ({id}) => helpers.setValue(id)

    return <EntitySearchComponent onValueChange={onValueChange}
                                  results={results}
                                  onSearchChanged={onSearch}
                                  isLoading={isLoading}
                                  disabled={disabled}
                                  {...props}/>

}