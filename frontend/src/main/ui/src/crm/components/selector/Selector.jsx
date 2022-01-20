import React from 'react';
import {useField} from "formik";

export const Selector = ({children, ...props}) => {

    const [field, meta] = useField(props)

    return (
        <>
            <select {...field}>
                {props.placeholder && <option value={''}>{props.placeholder}</option>}
                {children}
            </select>
            {meta.touched && meta.error ? (
                <div>{meta.error}</div>
            ) : null}
        </>
    )
}

