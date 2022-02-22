import React, {useState} from "react";
import ReactAvatarEditor from "react-avatar-edit";
import {Button, Container} from "semantic-ui-react";
import Popup from "reactjs-popup";

export const AvatarEditorPopup = React.forwardRef((props, ref) => {
    const {
        onChange,
    } = props

    const [src, setSrc] = useState(false)
    const [temp, setTemp] = useState(false)
    const [isOpen, setOpen] = useState(false)

    const close = () => {
        if (ref.current) {
            ref.current.value = null
        }
        setSrc(false)
        setOpen(false)
    }
    const open = () => setOpen(true)

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
            <input hidden={true} type={'file'} onChange={handleFileInput} ref={ref}/>
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
})