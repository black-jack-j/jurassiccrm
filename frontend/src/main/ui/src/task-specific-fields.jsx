import React from "react"
import {Field, useFormikContext} from "formik";


export const TaskTypeSpecific = (props) => {
    const {
        values: {taskType}
    } = useFormikContext();
    return getSpecificFields(taskType)
}

function getSpecificFields(taskType) {
    switch (taskType) {
        case 'INCUBATION': return incubationFields()
        case 'RESEARCH': return researchFields()
        default: return <div>Choose Task Type</div>
    }
}

export const incubationFields = ()  => {
    return (
        <React.Fragment>
            <label htmlFor={"species"}>Species:</label>
            <Field name="species" as="select">
                <option>Tyrannosaurus</option>
                <option>Triceratops</option>
            </Field>
        </React.Fragment>
    )
}

export const researchFields = () => {
    return (
        <React.Fragment>
            <label htmlFor={"purpose"}>Purpose:</label>
            <Field name="purpose" type="text"/>
        </React.Fragment>
    )
}