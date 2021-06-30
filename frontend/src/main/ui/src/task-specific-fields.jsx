import React from "react"
import {Field} from "formik";
import {SemanticFormikInputField, SemanticFormikSelectInputField} from "./utilities/SemanticUITOFormik";
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
            <Field name="species"
                   label="Species"
                   options={incubationSpeciesOptions}
                   placeholder="Select species" component={SemanticFormikSelectInputField}/>
            {touched.species && errors.species ? <Message negative>{errors.species}</Message> : null}
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