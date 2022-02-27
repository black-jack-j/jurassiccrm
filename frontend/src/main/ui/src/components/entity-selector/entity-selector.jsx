import {useField} from "formik";
import {EntityContainerWithSelect} from "../entity-container-with-select/entity-container-with-select";
import React from "react";

const idMapper = entity => entity.id

export const EntitySelector = props => {

    const {
        name,
        options = [],
        title,
        popupTitle
    } = props

    const [field, meta, helpers] = useField(name)

    const selectedIds = new Set(field.value && field.value.map(idMapper) || [])

    const push = options => {
        const value = field.value || []
        helpers.setValue([...value, ...options])
    }

    const remove = id => {
        const selectedValues = field.value.filter(entity => entity.id !== id)
        helpers.setValue(selectedValues)
    }

    const availableOptions = options.filter(option => !selectedIds.has(option.id))

    return(
        <EntityContainerWithSelect
            push={push}
            remove={remove}
            items={field.value}
            options={availableOptions}
            title={title}
            popupTitle={popupTitle}
        />
    )

}