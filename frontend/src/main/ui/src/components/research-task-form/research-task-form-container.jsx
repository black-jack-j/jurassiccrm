import {ResearchTaskForm} from "./research-task-form";
import React from "react";
import {TASK_PRIORITY_ID} from "../aviary-creation-task-form/fieldNames";

const entityToOptions = ({id, name}) => ({key: id, text: name, value: {value: id, text: name}})

const mapper = array => array.map(entityToOptions)


export const ResearchTaskFormContainer = props => {

    const {
        prioritiesReader,
        ...rest
    } = props

    const priorities = prioritiesReader(mapper)

    const innerProps = {
        ...rest,
        [TASK_PRIORITY_ID]: {
            ...rest[TASK_PRIORITY_ID],
            options: priorities
        }
    }


    return <ResearchTaskForm {...innerProps}/>

}