import {Formik} from "formik";
import {Form, Input, ResetButton, SubmitButton, TextArea} from "formik-semantic-ui-react";
import {TASK_ASSIGNEE_ID, TASK_DESCRIPTION, TASK_NAME, TASK_PRIORITY_ID} from "../incubation-task-form/fieldNames";
import {AssigneeSearchComponent} from "../task/assignee/assignee-search-component";
import {TaskTOTaskTypeEnum as TaskType} from "../../generatedclient/models";
import React from "react";
import {RESEARCH_GOAL} from "../task/form/subform/research/fieldsNames";
import {FormikSelect} from "../formik-select/formik-select";

export const ResearchTaskForm = props => {

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
                    taskType={TaskType.Research}
                    name={TASK_ASSIGNEE_ID}
                    placeholder={translations(`field.${TASK_ASSIGNEE_ID}.placeholder`)}
                    {...rest[TASK_ASSIGNEE_ID]}
                />

                <FormikSelect
                    name={TASK_PRIORITY_ID}
                    placeholder={translations(`field.${TASK_PRIORITY_ID}.placeholder`)}
                    {...props[TASK_PRIORITY_ID]}
                />

                <Input
                    name={RESEARCH_GOAL}
                    placeholder={translations(`field.${RESEARCH_GOAL}.placeholder`)}
                    {...props[RESEARCH_GOAL]}/>

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