import {Modal, ModalContent} from "semantic-ui-react";
import React from "react";
import {close, open, selectTaskEditorPopupState} from "./edit-task-popup-slice";
import {useDispatch, useSelector} from "react-redux";
import {EditTaskFormContainer} from "../../edit-task-form/edit-task-form";


export const EditTaskPopup = () => {

    const {open: isOpen, taskType, taskId} = useSelector(selectTaskEditorPopupState)

    const dispatch = useDispatch()

    return (
        <Modal open={isOpen}
               onClose={() => dispatch(close())}
               onOpen={() => dispatch(open())}>

            <ModalContent>
                {
                    taskType &&
                        <EditTaskFormContainer
                            taskId={taskId}
                            taskType={taskType}
                            onCancel={() => dispatch(close())}
                            onSubmit={() => dispatch(close())}
                        />
                }
            </ModalContent>

        </Modal>
    )
}