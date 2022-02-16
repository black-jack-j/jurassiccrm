import React from "react";
import 'semantic-ui-css/semantic.min.css'
import {TaskPanel} from "./components/task/task-panel"
import {DocumentPanel} from "./components/document/document-panel";

import './i18n'
import {useTranslation} from "react-i18next";
import {Menu} from "./components/menu/menu";
import {SecurityPanel} from "./components/security-panel/security-panel";
import {WorkspaceContainer} from "./components/workspace/workspace";



export const CRM = () => {

    const {t, i18n} = useTranslation()

    const tabs = [
        {key: 'task', text: t('crm.tab.task.name'), render: () => <TaskPanel/>},
        {key: 'document', text: t('crm.tab.document.name'), render: () => <DocumentPanel/>},
        {key: 'security', text: t('crm.tab.security.name'), render: () => <SecurityPanel/>},
        {key: 'workspace', text: t('crm.tab.workspace.name'), render: () => <WorkspaceContainer/>}
    ]

    return <Menu options={tabs}/>
}
