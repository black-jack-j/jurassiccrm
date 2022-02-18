import React from "react";
import {DINOSAUR_STATUS, DINOSAUR_TYPE_ID} from "./fieldNames";
import {DinosaurPassportForm} from "./dinosaur-passport-form";

export const DinosaurPassportFormContainer = props => {

    const {
        dinosaurTypesReader,
        dinosaurStatusesReader,
        ...other
    } = props

    const dinosaurTypes = dinosaurTypesReader()
    const dinosaurStatuses = dinosaurStatusesReader()

    const innerProps = {
        ...other,
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: type.id}))
        },
        [DINOSAUR_STATUS]: {
            ...props[DINOSAUR_STATUS],
            options: dinosaurStatuses.map(status => ({key: status, text: status, value: status}))
        }
    }

    return (
        <DinosaurPassportForm {...innerProps} />
    )
}