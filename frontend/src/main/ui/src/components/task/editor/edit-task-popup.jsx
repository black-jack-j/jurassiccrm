import {Modal, ModalContent} from "semantic-ui-react";
import React from "react";
import {Editor} from "./edit-task-form";
import {close, open, selectTaskEditorPopupState} from "./edit-task-popup-slice";
import {useDispatch, useSelector} from "react-redux";


export const EditTaskPopup = () => {

    const state = useSelector(selectTaskEditorPopupState)

    const dispatch = useDispatch()

    return (
        <Modal open={state.open}
               onClose={() => dispatch(open())}
               onOpen={() => dispatch(close())}>

            <ModalContent>
                <Editor task={state.task} onCancel={() => dispatch(close())} onSubmit={() => dispatch(close())}/>
            </ModalContent>

        </Modal>
    )
}