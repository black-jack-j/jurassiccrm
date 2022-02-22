import {useField} from "formik";
import {AvatarEditorPopup} from "../avatar-editor-popup/avatar-editor-popup";
import React from "react";

export const FormikAvatarSelector = props => {

    const {
        name,
        ref
    } = props

    const [field, meta, helpers] = useField(name)

    return (
        <AvatarEditorPopup onChange={avatar => helpers.setValue(avatar)} ref={ref}/>
    )

}

export const FormikAvatarSelectorPreview = props => {

    const {
        Placeholder,
        name
    } = props

    const [field] = useField(name)

    const avatar = field.value

    if (avatar) {
        return <img src={avatar} alt={'avatar'} style={{width: "64px", height: "64px", display: "inline-block"}}/>
    } else {
        return <Placeholder/>
    }

}