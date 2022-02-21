import React, {useContext, useState} from "react";
import UserContext from "../../user/user-context";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import {useTranslation} from "react-i18next";
import {CreateTaskForm} from "./create-task-form";

export const CreateTaskFormContainer = props => {

    const {
        onSubmit,
        onCancel
    } = props

    const {user} = useContext(UserContext)

    const availableTaskTypes = user ?
        Object.values(TaskType)
            .filter(taskType => user.canEditTaskOfType(taskType)) : []

    const defaultTaskType = availableTaskTypes && availableTaskTypes[0]

    const [taskType, setTaskType] = useState(defaultTaskType)


    const {t} = useTranslation('translation', {keyPrefix: 'crm.task.type'})

    const taskTypeOptions = availableTaskTypes.map(type => ({key: type, value: type, text: t(type)}))

    return (
        <CreateTaskForm
            onCancel={onCancel}
            onSubmit={onSubmit}
            taskType={taskType}
            taskTypeOptions={taskTypeOptions}
            taskTypeChanged={setTaskType}
            taskTypeText={t(taskType)}
        />
    )

}