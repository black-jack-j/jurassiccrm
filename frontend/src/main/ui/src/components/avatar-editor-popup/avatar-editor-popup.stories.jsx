import ReactAvatarEditor from "react-avatar-edit"
import React, {createRef, useState} from "react";
import {Icon} from "semantic-ui-react"
import {AvatarEditorPopup} from "./avatar-editor-popup";
import {UserIcon} from "../jurassic_icon/user/user-icon";

export default {
    title: 'Avatar Editor',
    components: [ReactAvatarEditor, AvatarEditorPopup]
}

export const Default = () => {

    const [img, setImg] = useState(false)
    const [preview, setPreview] = useState(false)

    const inputRef = createRef()

    const handleFileInput = (event) => {
        if (event.target.value === "") {
            setImg(false)
        } else {
            const reader = new FileReader()
            reader.onload = event => {
                const image = new Image()
                image.src = event.target.result
                setImg(image)
            }
            reader.readAsDataURL(event.target.files[0])
        }
    }

    const handleClose = () => {
        inputRef.current.value = null
        setImg(false)
    }

    return (
        <>
            {preview && (
                <img src={preview} alt={'avatar preview'} style={{width: '42px', height: '42px'}}/>
            ) || (
                <Icon name={'user'} circular size={'large'}/>
            )
            }
            <input ref={inputRef} type={'file'} onChange={handleFileInput}/>
            {img && (
                <ReactAvatarEditor
                    onClose={handleClose}
                    onCrop={setPreview}
                    img={img}
                    width={150}
                    height={150}
                    imageWidth={150}
                />
            )}
        </>
    )

}

export const DefaultEditor = () => {

    return <AvatarEditorPopup Placeholder={() => <UserIcon circular size={'big'}/>}/>

}

