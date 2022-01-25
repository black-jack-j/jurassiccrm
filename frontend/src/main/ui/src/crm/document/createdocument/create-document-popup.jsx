import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentCreatorPopupState} from "./create-document-popup-slice";
import React from "react";
import {CreateDocumentFormContainer} from "./create-document-form-container";
import {Modal, ModalContent} from "semantic-ui-react";

export const CreateDocumentPopup = () => {

    const open = useSelector(selectDocumentCreatorPopupState).open
    const documentType = useSelector(selectDocumentCreatorPopupState).documentType

    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())

    return (
        <Modal open={open} onClose={() => closePopup()}>
            <ModalContent>
                <CreateDocumentFormContainer onSubmit={closePopup}
                                             onCancel={closePopup}
                                             type={documentType}/>
            </ModalContent>
        </Modal>
    )

}