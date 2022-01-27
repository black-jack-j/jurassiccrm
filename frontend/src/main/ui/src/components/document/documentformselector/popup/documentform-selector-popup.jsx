import {useDispatch, useSelector} from "react-redux";
import {close, selectDocumentFormSelectorPopupOpen} from "./documentform-selector-popup-slice";
import {Modal, ModalContent} from "semantic-ui-react";
import React from "react";
import {DocumentFormSelectorContainer} from "../container/documentform-selector-container";

export const DocumentFormSelectorPopup = () => {

    const open = useSelector(selectDocumentFormSelectorPopupOpen)
    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())

    return (
        <Modal open={open} onClose={closePopup}>
            <ModalContent>
                <DocumentFormSelectorContainer onSubmit={closePopup}
                                             onCancel={closePopup}/>
            </ModalContent>
        </Modal>
    )

}