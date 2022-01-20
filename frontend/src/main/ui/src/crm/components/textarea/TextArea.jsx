import {useField} from "formik";
import React from "react";

export const TextArea = (props) => {

    const [field, meta] = useField(props)

    return (
        <>
            <textarea {...field} {...props}/>
            {meta.touched && meta.error ? (
                <div>{meta.error}</div>
            ) : null}
        </>
    )
}
