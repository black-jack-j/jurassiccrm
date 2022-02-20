import {Formik} from "formik";
import {Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {
    AVIARY_SQUARE,
    AVIARY_TYPE_ID,
    TASK_ASSIGNEE_ID,
    TASK_DESCRIPTION,
    TASK_NAME,
    TASK_PRIORITY_ID
} from "./fieldNames";
import {AssigneeSearchComponent} from "../task/assignee/assignee-search-component";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import {FormikSelect} from "../formik-select/formik-select";
import React from "react";

export const AviaryCreationTaskForm = props => {
    const {
        onSubmit,
        onCancel,
        initialValues,
        translations,
        ...rest
    } = props

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}>
            <Form>
                <Input
                    name={TASK_NAME}
                    placeholder={translations(`field.${TASK_NAME}.placeholder`)}
                    {...rest[TASK_NAME]}
                />

                <AssigneeSearchComponent
                    taskType={TaskType.AviaryCreation}
                    name={TASK_ASSIGNEE_ID}
                    placeholder={translations(`field.${TASK_ASSIGNEE_ID}.placeholder`)}
                    {...rest[TASK_ASSIGNEE_ID]}
                />

                <FormikSelect
                    name={TASK_PRIORITY_ID}
                    placeholder={translations(`field.${TASK_PRIORITY_ID}.placeholder`)}
                    {...props[TASK_PRIORITY_ID]}
                />

                <FormikSelect
                    name={AVIARY_TYPE_ID}
                    placeholder={translations(`field.${AVIARY_TYPE_ID}.placeholder`)}
                    {...rest[AVIARY_TYPE_ID]}
                />

                <Input name={AVIARY_SQUARE}
                       placeholder={translations(`field.${AVIARY_SQUARE}.placeholder`)}
                       {...props[AVIARY_SQUARE]}
                />

                <TextArea
                    name={TASK_DESCRIPTION}
                    placeholder={translations(`field.${TASK_DESCRIPTION}.placeholder`)}
                    {...rest[TASK_DESCRIPTION]}
                />

                <SubmitButton positive>{translations('submit')}</SubmitButton>
                <ResetButton negative onClick={onCancel}>{translations('cancel')}</ResetButton>
            </Form>
        </Formik>
    )
}