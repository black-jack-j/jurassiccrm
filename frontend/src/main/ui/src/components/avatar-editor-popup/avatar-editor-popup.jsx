import React, {createRef, useState} from "react";
import ReactAvatarEditor from "react-avatar-edit";
import {Button, Container, ModalContent} from "semantic-ui-react";
import Popup from "reactjs-popup";

export const AvatarEditorPopup = props => {

    const {
        onChange,
    } = props

    const [src, setSrc] = useState(false)
    const [temp, setTemp] = useState(false)
    const [isOpen, setOpen] = useState(false)

    const close = () => {
        if (inputRef.current) {
            inputRef.current.value = null
        }
        setSrc(false)
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
        <Container className={'avatar-editor'}>
            <Button type={'button'} onClick={() => {inputRef.current.click()}}>Select Icon</Button>
            <input hidden={true} type={'file'} onChange={handleFileInput} ref={inputRef}/>
            <Popup open={isOpen} onOpen={open} onClose={close} nested>
                {src && (
                    <ReactAvatarEditor
                        onClose={close}
                        onCrop={setTemp}
                        src={src}
                        width={250}
                        height={250}
                    />
                )}
                <Button type={'button'} onClick={() => {
                    onChange && onChange(temp)
                    close()
                }} positive>Ok</Button>
            </Popup>
        </Container>
    )

}