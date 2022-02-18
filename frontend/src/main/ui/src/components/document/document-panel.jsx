import React from "react";
import {Button, Tab} from "semantic-ui-react";
import {useDispatch} from "react-redux";

import {open as openDocumentFormSelector} from './documentformselector/popup/documentform-selector-popup-slice'
import {DocumentFormSelectorPopup} from "./documentformselector/popup/documentform-selector-popup";
import {useTranslation} from "react-i18next";
import {DocumentDashboardContainer} from "../document-dashboard/document-dashboard-container";
import {CreateDocumentFormPopup} from "../create-document-form-popup/create-document-form-popup";

const DocumentPanelContent = () => {

    const {t} = useTranslation()

    const dispatch = useDispatch()

    return (
        <>
            <DocumentDashboardContainer />
            <Button onClick={() => dispatch(openDocumentFormSelector())} type={'button'}>{t('crm.document.panel.createdocument.create')}</Button>
            <DocumentFormSelectorPopup/>
            <CreateDocumentFormPopup />
        </>
    )

}

export const DocumentPanel = () => <Tab.Pane><DocumentPanelContent/></Tab.Pane>