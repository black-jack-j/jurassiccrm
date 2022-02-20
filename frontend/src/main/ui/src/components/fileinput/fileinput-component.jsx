import {Input} from "semantic-ui-react";
import {useField} from "formik";
import React, {useEffect, useState} from "react";


export const FileInput = ({name, ...props}) => {

    const [field, meta, helpers] = useField(name)

    const handleFileChanged = (e) => {
        helpers.setValue(e.currentTarget.files[0])
    }

    return (
        <>
            <Input type={'file'} onChange={handleFileChanged}/>
        </>
    )

}