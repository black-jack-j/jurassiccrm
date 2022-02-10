import {Modal, ModalContent} from "semantic-ui-react";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {close as closePopup, open as openPopup, selectTaskCreatorPopupOpened} from "./create-task-popup-slice";
import {CreateTaskFormContainer} from "../create-task-form-container";

export const CreateTaskPopup = () => {
    const open = useSelector(selectTaskCreatorPopupOpened)
    const dispatch = useDispatch()

    return (
        <>
            <Modal open={open}
                   onClose={() => dispatch(closePopup())}
                   onOpen={() => dispatch(openPopup())}>

                <ModalContent>
                    <CreateTaskFormContainer onCancel={() => dispatch(closePopup())}
                                    onSubmit={() => dispatch(closePopup())}/>
                </ModalContent>

            </Modal>
        </>
    )
}