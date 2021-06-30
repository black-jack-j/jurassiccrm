import React, {useState} from "react";
import {Field, useFormikContext, withFormik} from "formik";
import {TaskTypeSpecific} from "./task-specific-fields";
import axios from "axios";
import 'semantic-ui-css/semantic.min.css'
import {Button, Form} from "semantic-ui-react";
import {
    SemanticFormikInputField,
    SemanticFormikSelectInputField,
    SemanticFormikTextAreaInputField
} from "./utilities/SemanticUITOFormik";

const SignupForm = ({possibleAssignees,
                        taskTypes,
                        handleSubmit,
                        values,
                        errors}) => {

    return (
            <Form onSubmit={handleSubmit}>

                <Field name="summary" label="Task Summary" component={SemanticFormikInputField}/>

                <Field name="taskType"
                       label="Task Type"
                       options={taskTypes ? renderTaskTypes(taskTypes) : []}
                       placeholder="Select type"
                       component={SemanticFormikSelectInputField}/>

                <Field name="assigneeId"
                       label="Assignee"
                       options={possibleAssignees ? renderPossibleAssignees(possibleAssignees) : []}
                       placeholder="Select assignee"
                       component={SemanticFormikSelectInputField}/>

                <Field name="description" label="Description"
                       component={SemanticFormikTextAreaInputField}
                       placeholder="Optional description"/>

                <TaskTypeSpecific taskType={values.taskType}/>

                <Button type="submit" color="blue">Submit</Button>
            </Form>

    );
};

const renderPossibleAssignees = (possibleAssignees) => {
    return possibleAssignees.map(assignee => {
        return {key: assignee.id, text: assignee.username, value: assignee.id}
    })
}

const renderTaskTypes = (taskTypes) => {
    return taskTypes.map(taskType => {
        return {
            key: taskType,
            text: taskType,
            value: taskType
        }
    })
}

const submit = values => {
    axios.post(`/task/${values.taskType}`,
        {
            ...{
                summary: values.summary,
                description: values.description,
                assigneeId: values.assigneeId
            }, ...values
        }
    )
        .then(res => window.location = "/task")
        .catch(err => console.log("validation failed"))
}

const validate = values => {
    const errors = {}
    if (!values.summary) {
        errors.summary = 'Required'
    } else if (values.summary.length > 128) {
        errors.summary = 'Size must be lower or equal 128'
    }

    if (!values.taskType) {
        errors.taskType = 'Required'
    }

    if (!values.assigneeId) {
        errors.assigneeId = 'Required'
    }

    if(values.description && values.description.length > 255) {
        errors.description = 'Size must be lower or equal 255'
    }
    return errors;
}

export default withFormik({
    mapPropsToValues: props => {
        return {
            summary: props.summary,
            description: props.description,
            taskType: props.taskType,
            assigneeId: props.assigneeId
        };
    },
    validate,
    displayName: "New Task",
    handleSubmit: submit
})(SignupForm);