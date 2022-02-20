import React, {useContext} from 'react';
import {Form} from "formik-semantic-ui-react"
import {Header} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import ApiContext from "../../../api";
import {withType} from "../../create-task-form/utils";

export const CreateTaskForm = ({onCancel, onSubmit, taskType}) => {

    const [Form, serialize, initialValues] = withType(taskType)

    const API = useContext(ApiContext)

    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.create.${taskType}`})

    const submit = values => {

        API.task.createTask(
            {taskType, body: values},
            {body: JSON.stringify(serialize(values))}).
        then(() => {
            onSubmit && onSubmit(values)
        })

    }

    if (taskType) {
        return (
            <>
                <Header as='h2'>{t('title')}</Header>
                <Form onSubmit={submit} onCancel={onCancel} initialValues={initialValues} translations={t}/>
            </>
        )
    } else {
        return <div/>
    }

}