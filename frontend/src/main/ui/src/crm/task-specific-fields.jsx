import React from "react"
import {Field} from "formik";
import {SemanticFormikInputField, SemanticFormikSelectInputField} from "../utilities/SemanticUITOFormik"
import {Container, Message} from "semantic-ui-react";


export const TaskTypeSpecific = ({taskType, touched, errors}) => {
    return (
        <Container>
            {getSpecificFields(taskType, touched, errors)}
        </Container>
    )
}

function getSpecificFields(taskType, touched, errors) {

    switch (taskType) {
        case 'INCUBATION': return incubationFields(touched, errors)
        case 'RESEARCH': return researchFields(touched, errors)
        default: return <div>Choose Task Type</div>
    }
}

const incubationSpeciesOptions = [
    {key: 't', text: 'Tyrannosaurus', value: 'Tyrannosaurus'},
    {key: 't2', text: 'Triceratops', value: 'Triceratops'},
]

export const incubationFields = (touched, errors)  => {
    return (
        <React.Fragment>
            <Field name="dinosaurType"
                   label="Species"
                   options={incubationSpeciesOptions}
                   placeholder="Select dinosaurType" component={SemanticFormikSelectInputField}/>
            {touched.dinosaurType && errors.dinosaurType ? <Message negative>{errors.dinosaurType}</Message> : null}
        </React.Fragment>
    )
}

export const researchFields = (touched, errors) => {
    return (
        <React.Fragment>
            <Field name="purpose" label="Purpose" component={SemanticFormikInputField}/>
            {touched.purpose && errors.purpose ? <Message negative>{errors.purpose}</Message> : null}
        </React.Fragment>
    )
}
