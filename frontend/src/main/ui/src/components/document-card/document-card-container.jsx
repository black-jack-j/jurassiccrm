import {DocumentCard} from "./document-card";
import React from "react";
import {useTranslation} from "react-i18next";

export const DocumentCardContainer = ({type, ...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.type'})

    return (
        <DocumentCard type={t(`${type}`)} {...props} />
    )

}