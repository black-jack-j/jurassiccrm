import {useDispatch} from "react-redux";
import {updateDocument} from "../update-document-form-popup/update-document-form-popup-slice";
import {EditableDocumentCard} from "./editable-document-card";
import React from "react";
import {useTranslation} from "react-i18next";
import {viewDocument} from "../document-viewer/document-viewer-slice";

export const EditableDocumentCardContainer = props => {

    const {
        type,
        id,
        name
    } = props

    const dispatch = useDispatch()

    const editCard = () => dispatch(updateDocument({documentType: type, id}))
    const selectDocument = () => dispatch(viewDocument({documentType: type, id}))

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.type'})

    return (
        <EditableDocumentCard name={name} type={t(type)} onEdit={editCard} onSelect={selectDocument}/>
    )

}