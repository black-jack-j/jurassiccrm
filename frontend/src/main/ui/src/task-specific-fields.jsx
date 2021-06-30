import React from "react"
import {Field} from "formik";
import {SemanticFormikInputField, SemanticFormikSelectInputField} from "./utilities/SemanticUITOFormik";


export const TaskTypeSpecific = ({taskType}) => {
    return getSpecificFields(taskType)
}

function getSpecificFields(taskType) {
    switch (taskType) {
        case 'INCUBATION': return incubationFields()
        case 'RESEARCH': return researchFields()
        default: return <div>Choose Task Type</div>
    }
}

const incubationSpeciesOptions = [
    {key: 't', text: 'Tyrannosaurus', value: 'Tyrannosaurus'},
    {key: 't2', text: 'Triceratops', value: 'Triceratops'},
]

export const incubationFields = ()  => {
    return (
            <Field name="species"
                   label="Species"
                   options={incubationSpeciesOptions}
                   placeholder="Select species" component={SemanticFormikSelectInputField}/>
    )
}

export const researchFields = () => {
    return (
        <Field name="purpose" label="Purpose" component={SemanticFormikInputField}/>
    )
}