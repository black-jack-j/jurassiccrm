import React from 'react'
import {SemanticFormikSelectInputField} from "./SemanticUITOFormik";
import {withFormik} from "storybook-formik";

export default {
    title: 'Semantic UI Formik controls',
    decorators: [withFormik]
}

const typeOptions = [
    {key: '1', value: 1, text: 'One'},
    {key: '2', value: 2, text: 'Two'},
    {key: '3', value: 3, text: 'Three'},
]

export const inputField = () => (
    <>
        <SemanticFormikSelectInputField name="type" options={typeOptions} placeholder={"Number"}/>
    </>
)

inputField.parameters = {
    formik: {
        initialValues: {
            username: ''
        }
    }
}