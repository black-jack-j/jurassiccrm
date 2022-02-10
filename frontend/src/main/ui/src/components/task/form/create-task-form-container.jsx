import {CreateTaskForm} from "./create-task-form";
import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../api";
import {withType} from "./subform/subform";
import commonInitialValues from './initialValues'
import {TaskTOTaskTypeEnum} from "../../../generatedclient/models";
import {TASK_PRIORITY_ID, TASK_TYPE} from "./fieldNames";
import {useTranslation} from "react-i18next";

const taskTypes = [TaskTOTaskTypeEnum.Incubation, TaskTOTaskTypeEnum.Research, TaskTOTaskTypeEnum.AviaryCreation]

export const CreateTaskFormContainer = ({onSubmit, onCancel}) => {

    const [taskType, setTaskType] = useState(TaskTOTaskTypeEnum.Incubation)
    const [priorities, setPriorities] = useState([])
    const [SubForm, subFormInitialValues, toJSON] = withType(taskType)

    const API = useContext(ApiContext)
    const {t} = useTranslation('translation', {keyPrefix: 'crm.task.type'})

    useEffect(() => {
        API.task.getPriorities()
            .then(priorities => {
                console.log(priorities)
                return priorities
            })
            .then(setPriorities)
            .catch(console.error)
    }, [])


    const createTaskOnSubmit = values => {
        onSubmit && onSubmit(values)
        console.log(values)
        API.task.createTask({taskType: values.taskType, body: undefined}, {body: JSON.stringify(toJSON(values))})
            .then(console.log)
            .catch(console.error)
    }

    const initialValues = {
        [TASK_TYPE]: taskType,
        ...subFormInitialValues,
        ...commonInitialValues,
    }

    const props = {
        [TASK_PRIORITY_ID]: {
            options: priorities.map(type => ({key: type.id, value: type.id, text: type.name}))
        },
        [TASK_TYPE]: {
            options: taskTypes.map(type => ({key: type, value: type, text: t(`${type}`)}))
        },
    }

    return (
        <CreateTaskForm onSubmit={createTaskOnSubmit}
                        onCancel={onCancel}
                        setTaskType={setTaskType}
                        selectedTaskType={taskType}
                        formik={{initialValues}}
                        {...props}>
            <SubForm/>
        </CreateTaskForm>
    )
}