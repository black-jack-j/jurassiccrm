import React from "react";
import 'semantic-ui-css/semantic.min.css'
import {TaskPanel} from "./components/task/task-panel"

import './i18n'
import {useTranslation} from "react-i18next";
import {CrmSecurityPanel} from "./components/crm-security-panel/crm-security-panel";
import {WorkspaceContainer} from "./components/workspace/workspace";
import {Workspace} from "./pages/workspace";


export const CRM = () => {

    const {t, i18n} = useTranslation()

    const tabs = [
        {key: 'task', text: t('crm.tab.task.name'), render: () => <TaskPanel/>},
        {key: 'security', text: t('crm.tab.security.name'), render: () => <CrmSecurityPanel/>},
        {key: 'workspace', text: t('crm.tab.workspace.name'), render: () => <WorkspaceContainer/>}
    ]

    return <Workspace />
}
