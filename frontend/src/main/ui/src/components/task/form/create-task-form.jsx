import React from 'react';
import {Formik} from "formik";
import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react"
import {Header} from "semantic-ui-react";
import {TASK_ASSIGNEE_ID, TASK_DESCRIPTION, TASK_NAME, TASK_PRIORITY_ID, TASK_TYPE} from "./fieldNames";
import {useTranslation} from "react-i18next";
import {AssigneeSearchComponent} from "../assignee/assignee-search-component";

export const CreateTaskForm = ({onCancel, onSubmit, formik, selectedTaskType, setTaskType, children, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.task.form.create'})

    return (
        <>
            <Header as='h2'>{t('title')}</Header>
            <Formik enableReinitialize
                initialValues={formik.initialValues}
                onSubmit={onSubmit}>
                <Form>
                    <Select name={TASK_TYPE}
                            value={selectedTaskType}
                            placeholder={t(`field.${TASK_TYPE}.placeholder`)}
                            onChange={(event, {value}) => setTaskType(value)}
                            {...props[TASK_TYPE]}/>

                    <Input name={TASK_NAME}
                           placeholder={t(`field.${TASK_NAME}.placeholder`)}/>

                    <AssigneeSearchComponent taskType={selectedTaskType}
                                             name={TASK_ASSIGNEE_ID}
                                             placeholder={t(`field.${TASK_ASSIGNEE_ID}.placeholder`)}/>

                    <Select name={TASK_PRIORITY_ID}
                            placeholder={t(`field.${TASK_PRIORITY_ID}.placeholder`)}
                            {...props[TASK_PRIORITY_ID]}/>

                    {children}

                    <TextArea name={TASK_DESCRIPTION}
                              placeholder={t(`field.${TASK_DESCRIPTION}.placeholder`)}/>

                    <SubmitButton positive>{t('submit')}</SubmitButton>
                    <ResetButton negative onClick={onCancel}>{t('cancel')}</ResetButton>
                </Form>
            </Formik>
        </>
    )

}