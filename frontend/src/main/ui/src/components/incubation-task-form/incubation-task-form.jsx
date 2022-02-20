import {Form, Input, ResetButton, Select, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {Formik} from "formik";
import React from "react";
import {AssigneeSearchComponent} from "../task/assignee/assignee-search-component";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import {FormikSelect} from "../formik-select/formik-select";
import {DINOSAUR_TYPE_ID, TASK_ASSIGNEE_ID, TASK_DESCRIPTION, TASK_NAME, TASK_PRIORITY_ID} from "./fieldNames";

export const IncubationTaskForm = props => {

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
                    taskType={TaskType.Incubation}
                    name={TASK_ASSIGNEE_ID}
                    placeholder={translations(`field.${TASK_ASSIGNEE_ID}.placeholder`)}
                    {...rest[TASK_ASSIGNEE_ID]}
                />

                <Select
                    name={TASK_PRIORITY_ID}
                    placeholder={translations(`field.${TASK_PRIORITY_ID}.placeholder`)}
                    {...props[TASK_PRIORITY_ID]}
                />

                <FormikSelect
                    name={DINOSAUR_TYPE_ID}
                    placeholder={translations(`field.${DINOSAUR_TYPE_ID}.placeholder`)}
                    {...rest[DINOSAUR_TYPE_ID]}
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