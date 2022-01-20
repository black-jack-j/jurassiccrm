import React, {useState} from 'react';
import {Formik} from "formik";
import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react"
import {create as createTask} from "../API";
import {AVIARY_CREATION_TYPE, INCUBATION_TYPE, RESEARCH_TYPE, withType} from "./subform/subform";
import {SemanticFormikSelectInputField,} from "../../../utilities/SemanticUITOFormik";
import {Header} from "semantic-ui-react";

const formInitialValues = {
    taskName: '',
    assigneeId: '',
    priorityId: '',
    description: '',
}

const onSubmit = values => createTask(values).then(console.log).catch(console.error)

const taskTypeOptions = [INCUBATION_TYPE, AVIARY_CREATION_TYPE, RESEARCH_TYPE]

export const CreateTaskForm = ({onClose}) => {

    const [taskType, setTaskType] = useState(INCUBATION_TYPE)

    const [SubForm, subformInitialValues] = withType(taskType)

    const initialValues = {...formInitialValues, ...subformInitialValues, taskType}

    return (
        <>
            <Header as='h2'>Создать заявку</Header>
            <Formik enableReinitialize
                initialValues={initialValues}
                onSubmit={onSubmit}>
                <Form>
                    <Select name={'taskType'}
                            options={taskTypeOptions.map(type => ({key: type, value: type, text: type}))}
                            defaultValue={taskType}
                            onChange={(event, {value}) => setTaskType(value)}/>

                    <Input name='taskName' placeholder={'Название заявки'}/>
                    <Input name='assigneeId' placeholder={'Исполнитель'}/>
                    <SemanticFormikSelectInputField name='priorityId' placeholder='Приоритет' options={[{key: 'a', value: 'a', text: 'Simple text'}]}/>
                    <SubForm />
                    <TextArea name='description' placeholder='Описание'/>
                    <SubmitButton positive>Сохранить</SubmitButton>
                    <ResetButton negative onClick={onClose}>Отмена</ResetButton>
                </Form>
            </Formik>
        </>
    )

}