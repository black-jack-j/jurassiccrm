import {useField} from "formik";
import React from "react";

export const Input = (props) => {

    const [field, meta] = useField(props)

    return (
        <>
            <input {...field} {...props}/>
            {meta.touched && meta.error ? (
                <div>{meta.error}</div>
            ) : null}
        </>
    )
}
