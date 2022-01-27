import React from "react";
import {Button, Tab} from "semantic-ui-react";
import {useDispatch} from "react-redux";

import {CreateDocumentPopup} from "./createdocument/popup/create-document-popup";
import {open as openDocumentFormSelector} from './documentformselector/popup/documentform-selector-popup-slice'
import {DocumentFormSelectorPopup} from "./documentformselector/popup/documentform-selector-popup";
import {useTranslation} from "react-i18next";

const DocumentPanelContent = () => {

    const {t} = useTranslation()

    const dispatch = useDispatch()

    return (
        <>
            <Button onClick={() => dispatch(openDocumentFormSelector())} type={'button'}>{t('crm.document.panel.createdocument.create')}</Button>
            <DocumentFormSelectorPopup/>
            <CreateDocumentPopup />
        </>
    )

}

export const DocumentPanel = () => <Tab.Pane><DocumentPanelContent/></Tab.Pane>