import {DINOSAUR_TYPE_ID} from "../task/form/subform/incubation/fieldsNames";
import {IncubationTaskForm} from "./incubation-task-form";
import React from "react";

export const IncubationTaskFormContainer = props => {

    const {
        dinosaurTypesReader,
        ...rest
    } = props

    const dinosaurTypes = dinosaurTypesReader()

    const innerProps = {
        ...rest,
        [DINOSAUR_TYPE_ID]: {
            ...rest[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: {text: type.name, value: type.id}}))
        }
    }

    return <IncubationTaskForm {...innerProps}/>

}