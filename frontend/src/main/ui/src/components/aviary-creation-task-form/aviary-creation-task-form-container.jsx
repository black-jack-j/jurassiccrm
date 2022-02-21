import React from "react";
import {AVIARY_TYPE_ID, TASK_PRIORITY_ID} from "./fieldNames";
import {AviaryCreationTaskForm} from "./aviary-creation-task-form";

const entityToOptions = ({id, name}) => ({key: id, text: name, value: {value: id, text: name}})

const mapper = array => array.map(entityToOptions)

export const AviaryCreationTaskFormContainer = props => {

    const {
        aviaryTypesReader,
        prioritiesReader,
        ...rest
    } = props

    const aviaryTypes = aviaryTypesReader(mapper)
    const priorities = prioritiesReader(mapper)

    const innerProps = {
        ...rest,
        [AVIARY_TYPE_ID]: {
            ...rest[AVIARY_TYPE_ID],
            options: aviaryTypes
        },
        [TASK_PRIORITY_ID]: {
            ...rest[TASK_PRIORITY_ID],
            options: priorities
        }
    }

    return <AviaryCreationTaskForm {...innerProps}/>

}