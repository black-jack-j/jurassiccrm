import {Button, Modal, ModalContent} from "semantic-ui-react";
import {CreateTaskForm} from "./create-task-form";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {
    close as closePopup,
    open as openPopup,
    selectTaskCreatorPopupOpened,
    toggle as togglePopupOpen
} from "./create-task-popup-slice";

export const CreateTaskPopup = () => {
    const open = useSelector(selectTaskCreatorPopupOpened)
    const dispatch = useDispatch()

    return (
        <>
            <Modal open={open}
                   onClose={() => dispatch(closePopup())}
                   onOpen={() => dispatch(openPopup())}>

                <ModalContent>
                    <CreateTaskForm onCancel={() => dispatch(closePopup())}
                                    onSubmit={() => dispatch(closePopup())}/>
                </ModalContent>

            </Modal>
        </>
    )
}