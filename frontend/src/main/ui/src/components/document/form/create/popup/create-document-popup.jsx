import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentCreatorPopupState} from "./create-document-popup-slice";
import React from "react";
import {Modal, ModalContent} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import {withType} from "../utils";

export const CreateDocumentPopup = props => {

    const open = useSelector(selectDocumentCreatorPopupState).open
    const documentType = useSelector(selectDocumentCreatorPopupState).documentType

    const dispatch = useDispatch()
    const {t} = useTranslation()

    const closePopup = () => dispatch(close())

    const [DocumentFormContainer] = withType(documentType)

    const title = t(`crm.document.type.${documentType}`)

    return (
        <Modal open={open} onClose={() => closePopup()} style={{width: 600}}>
            <ModalContent>
                <DocumentFormContainer onSubmit={closePopup}
                                       onCancel={closePopup}
                                       type={documentType} title={title} {...props}/>
            </ModalContent>
        </Modal>
    )

}