import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentCreatorPopupState} from "./create-document-popup-slice";
import React from "react";
import {CreateDocumentFormContainer} from "./create-document-form-container";
import {Modal, ModalContent} from "semantic-ui-react";
import {
    AVIARY_PASSPORT,
    DINOSAUR_PASSPORT,
    RESEARCH_MATERIAL,
    TECHNOLOGICAL_MAP,
    THEME_ZONE_PROJECT
} from "./subform/createdocument-subform";

const getTitle = documentType => {
    switch (documentType) {
        case DINOSAUR_PASSPORT: {
            return 'Паспорт динозавра'
        }
        case AVIARY_PASSPORT: {
            return 'Паспорт вольера'
        }
        case RESEARCH_MATERIAL: {
            return 'Материалы исследования'
        }
        case TECHNOLOGICAL_MAP: {
            return 'Технологическая карта'
        }
        case THEME_ZONE_PROJECT: {
            return 'Проект тематической зоны'
        }
    }
}

export const CreateDocumentPopup = props => {

    const open = useSelector(selectDocumentCreatorPopupState).open
    const documentType = useSelector(selectDocumentCreatorPopupState).documentType

    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())

    const title = getTitle(documentType)

    return (
        <Modal open={open} onClose={() => closePopup()}>
            <ModalContent>
                <CreateDocumentFormContainer onSubmit={closePopup}
                                             onCancel={closePopup}
                                             type={documentType} title={title} {...props}/>
            </ModalContent>
        </Modal>
    )

}