import {DocumentCard} from "./document-card";
import React from "react";
import {useTranslation} from "react-i18next";
import {useDispatch} from "react-redux";
import {viewDocument} from "../document-viewer/document-viewer-slice";

export const DocumentCardContainer = ({type, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.type'})

    const dispatch = useDispatch()

    const selectDocument = () => dispatch(viewDocument({documentType: props.type, id: props.id}))

    return (
        <DocumentCard type={t(type)} onSelect={selectDocument} {...props} />
    )

}