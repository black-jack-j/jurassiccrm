import {Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {SemanticFormikSelectInputField} from "../../utilities/SemanticUITOFormik";
import {Formik} from "formik";
import React, {useContext} from "react";
import {Label, LabelGroup} from "semantic-ui-react";
import {AVIARY_CREATION_TYPE, INCUBATION_TYPE, RESEARCH_TYPE, withType} from "../form/subform/subform";
import {DINOSAUR_TYPE_ID} from "../form/subform/incubation/fieldsNames";
import {AVIARY_SQUARE, AVIARY_TYPE_ID} from "../form/subform/createaviary/fieldsNames";
import {RESEARCH_GOAL} from "../form/subform/research/fieldsNames";
import ApiContext from "../../../api";

const getTypeSpecificProps = (taskType, additionalParams) => {
    switch (taskType) {
        case INCUBATION_TYPE: {
            return {
                [DINOSAUR_TYPE_ID]: additionalParams[DINOSAUR_TYPE_ID]
            }
        }
        case AVIARY_CREATION_TYPE: {
            return {
                [AVIARY_TYPE_ID]: additionalParams[AVIARY_TYPE_ID],
                [AVIARY_SQUARE]: additionalParams[AVIARY_SQUARE]
            }
        }
        case RESEARCH_TYPE: {
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
