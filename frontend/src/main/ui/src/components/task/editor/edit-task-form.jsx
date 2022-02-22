import {Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {SemanticFormikSelectInputField} from "../../utilities/SemanticUITOFormik";
import {Formik} from "formik";
import React, {useContext} from "react";
import {Label, LabelGroup} from "semantic-ui-react";
import ApiContext from "../../../api";
import {DINOSAUR_TYPE_ID} from "../../dinosaur-passport-form/fieldNames";
import {AVIARY_SQUARE, AVIARY_TYPE_ID} from "../../aviary-creation-task-form/fieldNames";
import {RESEARCH_GOAL} from "../../research-task-form/fieldNames";
import {TaskTOTaskTypeEnum as TaskType} from "../../../generatedclient/models";
import withType from "../../create-task-form/utils";

const getTypeSpecificProps = (taskType, additionalParams) => {
    switch (taskType) {
        case TaskType.Incubation: {
            return {
                [DINOSAUR_TYPE_ID]: additionalParams[DINOSAUR_TYPE_ID]
            }
        }
        case TaskType.AviaryCreation: {
            return {
                [AVIARY_TYPE_ID]: additionalParams[AVIARY_TYPE_ID],
                [AVIARY_SQUARE]: additionalParams[AVIARY_SQUARE]
            }
        }
        case TaskType.Research: {
            return {
                [RESEARCH_GOAL]: additionalParams[RESEARCH_GOAL]
            }
        }
    }
}

const updateTaskProvider = API => (taskId, values) => API.task.updateTask({taskId, taskTO: {...values}}).then(console.log).catch(console.error)

export const Editor = ({task, onCancel, onSubmit}) => {

    const API = useContext(ApiContext)

    const {
        id,
        name,
        currentState,
        taskType,
        description,
        assigneeId,
        additionalParams,
        priorityId,
    } = task;

    const commonInitialValues = {
        name,
        description,
        assigneeId,
        priorityId
    }

    const typeSpecificValues = getTypeSpecificProps(taskType, additionalParams)

    const [SubForm, subformInitialValues, paramsFormatter] = withType(taskType)

    return (
        <>
            <Formik initialValues={{...commonInitialValues, ...typeSpecificValues}}
                    onSubmit={values => {
                        onSubmit()
                        updateTaskProvider(API)(id, {...values, additionalParams: paramsFormatter(values)})
                    }}>
                <Form>
                    <Input name='name' placeholder={'Название заявки'}/>
                    <LabelGroup>
                        <Label>{taskType}</Label>
                        <Label>{currentState}</Label>
                    </LabelGroup>
                    <Input name='assigneeId' placeholder={'Исполнитель'}/>
                    <SemanticFormikSelectInputField name='priorityId' placeholder='Приоритет' options={[{key: 'a', value: 'a', text: 'Simple text'}]}/>
                    <SubForm />
                    <TextArea name='description' placeholder='Описание'/>
                    <SubmitButton positive>Сохранить</SubmitButton>
                    <ResetButton negative onClick={onCancel}>Отмена</ResetButton>
                </Form>
            </Formik>
        </>

    )

}
