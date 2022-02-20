import {DINOSAUR_TYPE_ID} from "./fieldNames";
import {TechnologicalMapForm} from "./technologicalmap-form";
import React from "react";

export const TechnologicalMapFormContainer = props => {

    const {
        dinosaurTypesReader,
        ...other
    } = props

    const dinosaurTypes = dinosaurTypesReader()

    const innerProps = {
        ...other,
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: {value: type.id, text: type.name}}))
        },
    }

    return (
        <TechnologicalMapForm {...innerProps} />
    )

}