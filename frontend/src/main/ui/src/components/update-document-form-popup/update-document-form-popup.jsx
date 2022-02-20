import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentUpdaterPopupState} from "./update-document-form-popup-slice";
import React from "react";
import {Modal, ModalContent} from "semantic-ui-react";
import {UpdateDocumentForm} from "../update-document-form/update-document-form";


export const UpdateDocumentFormPopup = props => {

    const {open, documentType, id} = useSelector(selectDocumentUpdaterPopupState)

    const canRenderPopup = documentType && ((typeof id !== 'undefined') && (id || id === 0))

    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())

    return (
        <Modal open={open} onClose={() => closePopup()} style={{width: 600}}>
            <ModalContent>
                {canRenderPopup && <UpdateDocumentForm onSubmit={closePopup}
                                       onCancel={closePopup}
                                       documentType={documentType} id={id}/>}
            </ModalContent>
        </Modal>
    )

}