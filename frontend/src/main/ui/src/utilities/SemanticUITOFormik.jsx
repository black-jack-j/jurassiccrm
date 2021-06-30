import React from "react"
import {Form, Input} from "semantic-ui-react"

export const SemanticFormikInputField = props => {
    const {
        field: {
            // provided by Formik Field
            name
        },
        form: {
            // also provided by Formik Field
            setFieldValue
        },
        label
    } = props;

    return (
        <Form.Field>
            <label>{label}</label>
            <Input
                type="text"
                onChange={(event, {value}) => {
                    setFieldValue(name, value);
                }}
            />
        </Form.Field>
    );
};

export const SemanticFormikSelectInputField = props => {
    const {
        field: {
            // provided by Formik Field
            name
        },
        form: {
            // also provided by Formik Field
            setFieldValue
        },
        label // our own label prop
        ,
        options,
        placeholder
    } = props;

    return (
        <Form.Select label={label}
                     options={options}
                     placeholder={placeholder}
                     onChange={(event, {value}) => {
                         setFieldValue(name, value)
                     }}/>
    );
}

export const SemanticFormikTextAreaInputField = props => {
    const {
        field: {
            // provided by Formik Field
            name
        },
        form: {
            // also provided by Formik Field
            setFieldValue
        },
        label // our own label prop
        ,
        placeholder
    } = props;

    return (
        <Form.TextArea label={label}
                     placeholder={placeholder}
                       onChange={(event, {value}) => {
                           setFieldValue(name, value)
                       }}/>
    );
}