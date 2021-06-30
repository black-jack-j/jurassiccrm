import React from "react";
import ReactDOM from "react-dom";
import {ErrorMessage, Field, Form, Formik, useFormik} from "formik";
import {TaskTypeSpecific} from "./task-specific-fields";
import axios from "axios";

const SignupForm = ({possibleAssignees, taskTypes}) => {

    return (
        <Formik
            onSubmit={submit}
            initialValues={{
                summary: '',
                taskType: 'RESEARCH',
                assigneeId: '',
                description: ''
            }}
            validate={validate}>
            <Form>
                <label htmlFor="summary">Task Summary:</label>
                <Field name="summary" type="text"/>
                <ErrorMessage name="summary"/>

                <label htmlFor="taskType">Task Type:</label>
                <Field name="taskType" as="select">
                    {taskTypes && renderTaskTypes(taskTypes)}
                </Field>
                <ErrorMessage name="taskType"/>

                <label htmlFor="assigneeId">Assignee:</label>
                <Field name="assigneeId" as="select">
                    {possibleAssignees && renderPossibleAssignees(possibleAssignees)}
                </Field>
                <ErrorMessage name="assigneeId"/>

                <label htmlFor="description">Description:</label>
                <Field name="description" as="textarea"/>
                <ErrorMessage name="description"/>

                <TaskTypeSpecific/>

                <button type="submit">Submit</button>
            </Form>
        </Formik>

    );
};

const renderPossibleAssignees = (possibleAssignees) => {
    return possibleAssignees.map(assignee => <option value={assignee.id}>{assignee.username}</option>)
}

const renderTaskTypes = (taskTypes) => {
    return taskTypes.map(taskType => <option value={taskType}>{taskType}</option>)
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

class App extends React.Component{

    state = {
        possibleAssignees: [],
        taskTypes: []
    }

    componentDidMount() {
        this.getAssignees();
        this.getTaskTypes();
    }

    getAssignees() {
        axios.get('/task/assignee')
            .then(possibleAssignees => this.setState({possibleAssignees: possibleAssignees.data}))
    }

    getTaskTypes() {
        axios.get('/task/type')
            .then(taskTypes => this.setState({taskTypes: taskTypes.data}))
    }

    render() {
        return <SignupForm possibleAssignees={this.state.possibleAssignees} taskTypes={this.state.taskTypes}/>
    }
}

const rootElement = document.getElementById("create-task");
ReactDOM.render(<App />, rootElement);
