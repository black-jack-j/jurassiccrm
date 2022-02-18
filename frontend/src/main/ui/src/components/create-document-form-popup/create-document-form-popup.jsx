import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentCreatorPopupState} from "./create-document-form-popup-slice";
import React from "react";
import {Modal, ModalContent} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import {CreateDocumentForm} from "../create-document-form/create-document-form";

export const CreateDocumentFormPopup = props => {

    const open = useSelector(selectDocumentCreatorPopupState).open
    const documentType = useSelector(selectDocumentCreatorPopupState).documentType

    const dispatch = useDispatch()
    const {t} = useTranslation()

    const closePopup = () => dispatch(close())

    return (
        <Modal open={open} onClose={() => closePopup()} style={{width: 600}}>
            <ModalContent>
                {documentType && <CreateDocumentForm onSubmit={closePopup}
                                       onCancel={closePopup}
                                       documentType={documentType}/>}
            </ModalContent>
        </Modal>
    )

}