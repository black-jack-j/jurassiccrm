import {useField} from "formik";
import {EntityContainerWithSelect} from "../entity-container-with-select/entity-container-with-select";
import React from "react";

export const EntitySelector = props => {

    const {
        name,
        options,
        title
    } = props

    const [field, meta, helpers] = useField(name)

    const selectedIds = new Set(field.value)

    const push = options => {
        helpers.setValue([...field.value, ...options.map(option => option.id)])
    }

    const remove = index => {
        helpers.setValue([...field.value.slice(0, index), ...field.value.slice(index+1)])
    }

    const items = []
    const availableOptions = []

    options.forEach(option => {
        if (selectedIds.has(option.id)) {
            items.push(option)
        } else {
            availableOptions.push(option)
        }
    })

    return <EntityContainerWithSelect push={push}
                                      remove={remove}
                                      items={items}
                                      options={availableOptions}
                                      title={title}/>

}