import React, {useContext, useState} from 'react';
import {Header, Select} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";
import withType from "./utils";

export const CreateTaskForm = ({onCancel, onSubmit, taskTypeOptions, taskType, taskTypeChanged, taskTypeText}) => {

    const [Form, serialize, initialValues] = withType(taskType)

    const handleTaskTypeChanged = (e, {value}) => taskTypeChanged(value)

    const API = useContext(ApiContext)

    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.create`})

    const submit = values => {

        API.task.createTask(
            {taskType, body: serialize(values)},
            {body: JSON.stringify(serialize(values))}).
        then(() => {
            onSubmit && onSubmit(values)
        })

    }

    if (taskType) {
        return (
            <>
                <Header as='h2'>{t(`${taskType}.title`)}</Header>
                <Select
                    value={taskType}
                    text={taskTypeText}
                    options={taskTypeOptions}
                    placeholder={t(`field.taskType.placeholder.${taskType}`)}
                    onChange={handleTaskTypeChanged}
                />
                {taskType && <Form onSubmit={submit} onCancel={onCancel} initialValues={initialValues} translations={key => t(`${taskType}.${key}`)}/>}
            </>
        )
    } else {
        return <></>
    }

}