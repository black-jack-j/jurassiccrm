import {DINOSAUR_TYPE_ID} from "../task/form/subform/incubation/fieldsNames";
import {IncubationTaskForm} from "./incubation-task-form";
import React from "react";
import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

const entityToOptions = ({id, name}) => ({key: id, text: name, value: {value: id, text: name}})

const mapper = array => array.map(entityToOptions)

export const IncubationTaskFormContainer = props => {

    const {
        dinosaurTypesReader,
        prioritiesReader,
        ...rest
    } = props

    const dinosaurTypes = dinosaurTypesReader()
    const priorities = prioritiesReader(mapper)

    const innerProps = {
        ...rest,
        [DINOSAUR_TYPE_ID]: {
            ...rest[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: {text: type.name, value: type.id}}))
        },
        [TASK_PRIORITY_ID]: {
            ...rest[TASK_PRIORITY_ID],
            options: priorities
        }
    }

    return <IncubationTaskForm {...innerProps}/>

}