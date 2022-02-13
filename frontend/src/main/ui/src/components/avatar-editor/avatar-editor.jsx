import React, {createRef, useState} from "react";
import {UserIcon} from "../jurassic_icon/user/user-icon";
import ReactAvatarEditor from "react-avatar-edit";
import Popup from "reactjs-popup";
import {Button} from "semantic-ui-react";

const DefaultPlaceholder = () => <UserIcon size={'big'} circular/>

export const AvatarEditor = props => {

    const {
        Placeholder = DefaultPlaceholder,
        onChange
    } = props

    const [src, setSrc] = useState(false)
    const [avatar, setAvatar] = useState(false)
    const [temp, setTemp] = useState(false)
    const [isOpen, setOpen] = useState(false)

    const handleEditorCancel = () => {
        inputRef.current.value = null
        setSrc(false)
    }

    const close = () => {
        setOpen(false)
    }

    const open = () => setOpen(true)

    const inputRef = createRef()

    const handleFileInput = (event) => {
        if (event.target.value === "") {
            setSrc(false)
            close()
        } else {
            const reader = new FileReader()
            reader.onload = event => {
                setSrc(event.target.result)
            }
            open()
            reader.readAsDataURL(event.target.files[0])
        }
    }

    return (
        <div className={'avatar-editor'}>
            {avatar ? <img className={'avatar-editor__preview'} src={avatar} alt={'preview'} style={{width: "42px", height: "42px"}}/> : <Placeholder/>}
            <button onClick={() => {inputRef.current.click()}}>Select file</button>
            <input hidden={true} type={'file'} onChange={handleFileInput} ref={inputRef}/>
            <Popup modal open={isOpen} onOpen={open} onClose={close}>
                {src && (
                    <ReactAvatarEditor
                        onClose={() => {
                            handleEditorCancel()
                            close()
                        }}
                        onCrop={setTemp}
                        src={src}
                        width={250}
                        height={250}
                    />
                )}
                <Button type={'button'} onClick={() => {
                    close()
                    setAvatar(temp)
                    onChange && onChange(temp)
                }} positive>Ok</Button>
            </Popup>
        </div>
    )

}