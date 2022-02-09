import React, {useContext, useState} from 'react';
import {Formik} from "formik";
import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react"
import {AVIARY_CREATION_TYPE, INCUBATION_TYPE, RESEARCH_TYPE, withType} from "./subform/subform";
import {SemanticFormikSelectInputField,} from "../../utilities/SemanticUITOFormik";
import {Header} from "semantic-ui-react";

import ApiContext from "../../../api";
import {FormikUserSearchContainer} from "../../usersearch/usersearch-component-container";
import {TASK_ASSIGNEE_ID, TASK_DESCRIPTION, TASK_NAME, TASK_PRIORITY_ID} from "./fieldNames";

const formInitialValues = {
    name: '',
    assigneeId: '',
    priorityId: '',
    description: '',
}

const createTaskProvider = API => values => API.task.createTask({taskType: values.taskType, body: {...values}}).then(console.log).catch(console.error)

const taskTypeOptions = [INCUBATION_TYPE, AVIARY_CREATION_TYPE, RESEARCH_TYPE]

export const CreateTaskForm = ({onCancel, onSubmit}) => {

    const API = useContext(ApiContext)

    const [taskType, setTaskType] = useState(INCUBATION_TYPE)

    const [SubForm, subformInitialValues, paramsFormatter] = withType(taskType)

    const initialValues = {...formInitialValues, ...subformInitialValues, taskType}

    return (
        <>
            <Header as='h2'>Создать заявку</Header>
            <Formik enableReinitialize
                initialValues={initialValues}
                onSubmit={values => {
                    onSubmit()
                    createTaskProvider(API)({...values, additionalParams: {...paramsFormatter(values)}})
                }}>
                <Form>
                    <Select name={'taskType'}
                            options={taskTypeOptions.map(type => ({key: type, value: type, text: type}))}
                            defaultValue={taskType}
                            onChange={(event, {value}) => setTaskType(value)}/>

                    <Input name={TASK_NAME} placeholder={'Название заявки'}/>
                    <FormikUserSearchContainer name={TASK_ASSIGNEE_ID} placeholder={'Исполнитель'}/>
                    <SemanticFormikSelectInputField name={TASK_PRIORITY_ID}
                                                    placeholder='Приоритет' options={[{key: 'a', value: 'a', text: 'Simple text'}]}/>
                    <SubForm />
                    <TextArea name={TASK_DESCRIPTION} placeholder='Описание'/>
                    <SubmitButton positive>Сохранить</SubmitButton>
                    <ResetButton negative onClick={onCancel}>Отмена</ResetButton>
                </Form>
            </Formik>
        </>
    )

}