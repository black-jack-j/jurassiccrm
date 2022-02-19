import {useField} from "formik";
import {Select} from "formik-semantic-ui-react";
import React from "react";

export const FormikSelect = props => {

    const {
        name,
        options,
        ...rest
    } = props

    const [field, meta, helpers] = useField(name)

    return (
        <Select
            name={name}
            options={options}
            value={field.value.value}
            text={field.value.text}
            {...rest}
        />
    )

}