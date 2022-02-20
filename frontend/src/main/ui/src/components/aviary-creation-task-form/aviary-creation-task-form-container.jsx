import React from "react";
import {AVIARY_TYPE_ID} from "./fieldNames";
import {AviaryCreationTaskForm} from "./aviary-creation-task-form";

export const AviaryCreationTaskFormContainer = props => {

    const {
        aviaryTypesReader,
        ...rest
    } = props

    const aviaryTypes = aviaryTypesReader()

    const innerProps = {
        ...rest,
        [AVIARY_TYPE_ID]: {
            ...rest[AVIARY_TYPE_ID],
            options: aviaryTypes.map(type => ({key: type.id, text: type.name, value: {text: type.name, value: type.id}}))
        }
    }

    return <AviaryCreationTaskForm {...innerProps}/>

}