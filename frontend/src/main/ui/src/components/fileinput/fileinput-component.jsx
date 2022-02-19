import {Input} from "semantic-ui-react";
import {useField} from "formik";
import React, {useEffect, useState} from "react";


export const FileInput = ({name, ...props}) => {

    const [field, meta, helpers] = useField(name)
    const [url, setUrl] = useState(null)

    const handleFileChanged = (e) => {
        helpers.setValue(e.currentTarget.files[0])
    }

    useEffect(() => {
        if (!field.value) {
            setUrl(null)
            return
        }
        const reader = new FileReader()
        reader.onloadend = () => {
            setUrl(reader.result)
        }
        reader.readAsDataURL(field.value)
    }, [field.value])

    return (
        <>
            <Input type={'file'} onChange={handleFileChanged}/>
            {url && <a href={url} download={'attachment'}>Download Attachment</a>}
        </>
    )

}