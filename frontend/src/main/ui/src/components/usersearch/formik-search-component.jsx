import {useField} from "formik";
import React from "react";
import {EntitySearchComponent} from "../entitysearch/entity-search-component";

export const FormikSearchComponent = ({search, entityFieldSelector, valueFieldSelector, fieldName, ...props}) => {

    const [value, meta, helpers] = useField(fieldName)

    return <EntitySearchComponent entityFieldSelector={entityFieldSelector}
                                  valueFieldSelector={valueFieldSelector}
                                  onValueChange={helpers.setValue}
                                  search={search}/>

}