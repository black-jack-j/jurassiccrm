import React from "react";
import {Field, withFormik} from "formik";
import {TaskTypeSpecific} from "./task-specific-fields";
import axios from "axios";
import 'semantic-ui-css/semantic.min.css'
import {Grid, Button, Form, Message, Menu} from "semantic-ui-react";
import {
    SemanticFormikInputField,
    SemanticFormikSelectInputField,
    SemanticFormikTextAreaInputField
} from "../utilities/SemanticUITOFormik";

const SignupForm = ({possibleAssignees,
                        taskTypes,
                        handleSubmit,
                        values,
                        errors,
                        touched, resetForm}) => {

    return (
            <Form onSubmit={handleSubmit}>

                <Grid divided={'vertically'}>
                    <Grid.Column width={2}>
                        <Field name="summary" label="Task Summary" component={SemanticFormikInputField}/>
                        {touched.summary && errors.summary ? <Message negative>{errors.summary}</Message> : null}

                        <Field name="taskType"
                               label="Task Type"
                               options={taskTypes ? renderTaskTypes(taskTypes) : []}
                               placeholder="Select type"
                               component={SemanticFormikSelectInputField}/>
                        {touched.taskType && errors.taskType ? <Message negative>{errors.taskType}</Message> : null}

                        <Field name="assigneeId"
                               label="Assignee"
                               options={possibleAssignees ? renderPossibleAssignees(possibleAssignees) : []}
                               placeholder="Select assignee"
                               component={SemanticFormikSelectInputField}/>

                        {touched.assigneeId && errors.assigneeId ? <Message negative>{errors.summary}</Message> : null}

                        <Field name="description" label="Description"
                               component={SemanticFormikTextAreaInputField}
                               placeholder="Optional description"/>

                        {touched.description && errors.description ? <Message negative>{errors.description}</Message> : null}
                    </Grid.Column>
                    <Grid.Column stretched width={5}>
                        {values.taskType ? <TaskTypeSpecific taskType={values.taskType} errors={errors} touched={touched}/> : null}
                    </Grid.Column>
                </Grid>
                <Menu secondary>
                    <Menu.Menu position="right">
                        <Menu.Item>
                            <Button type="reset" color="red" onClick={() => resetForm({
                                values: {
                                    summary: '',
                                    assigneeId: '',
                                    description: '',
                                    purpose: '',
                                    species: ''
                                }
                            })}>Clear</Button>
                        </Menu.Item>
                        <Menu.Item>
                            <Button type="submit" color="blue">Submit</Button>
                        </Menu.Item>
                    </Menu.Menu>
                </Menu>
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
    } else if (values.taskType === 'RESEARCH' && !values.purpose) {
        errors.purpose = 'Required'
    } else if (values.taskType === 'INCUBATION' && !values.species) {
        errors.species = 'Required'
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
            assigneeId: props.assigneeId,
            species: props.species,
            purpose: props.purpose
        };
    },
    validate,
    displayName: "New Task",
    handleSubmit: submit
})(SignupForm);