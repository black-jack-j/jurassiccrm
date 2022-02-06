import {Input} from "semantic-ui-react";
import {useField} from "formik";
import React from "react";

export const FileInput = ({name, ...props}) => {

    const [value, meta, helpers] = useField(name)

    console.log(value)

    return (
        <Input type={'file'} onChange={(e) => helpers.setValue(e.currentTarget.files[0])}/>
    )

}