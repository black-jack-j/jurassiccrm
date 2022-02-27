import {MenuItem, TabPane} from "semantic-ui-react";
import {DocumentDashboardContainer} from "../document-dashboard/document-dashboard-container";
import React from "react";
import {useTranslation} from "react-i18next";

export const DocumentPane = () => {
    return (
        <TabPane className={'pane'}  attached={false}>

            <DocumentDashboardContainer />
        </TabPane>
    )
}

export const DocumentTabMenu = () => {

    const {t} = useTranslation()

    return (
        <MenuItem key={'document'}>
            {t('crm.tab.document.name')}
        </MenuItem>
    )

}