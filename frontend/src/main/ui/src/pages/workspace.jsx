import {Responsive, WidthProvider} from "react-grid-layout";
import React, {Suspense, useContext} from "react";

import {CRMHeader} from "../components/crm-header/crm-header";
import UserContext, {withCurrentUser} from "../user/user-context";
import {UserPane} from "../components/user-tab/user-tab";
import {GroupPane} from "../components/group-tab/group-tab";
import {MenuItem, Tab} from "semantic-ui-react";
import {DocumentPane} from "../components/document-tab/document-tab";
import {TaskPane} from "../components/task-tab/task-tab";
import {useTranslation} from "react-i18next";

const ResponsiveGridLayout = WidthProvider(Responsive)

const layouts = {
    xxl: [
        {i: "s", x: 0, y: 1, w: 12, h: 6, static: true},
        {i: "h", x: 0, y: 0, w: 12, h: 1, static: true}
    ],
    xl: [
        {i: "s", x: 0, y: 1, w: 12, h: 6, static: true},
        {i: "h", x: 0, y: 0, w: 12, h: 1, static: true},
    ]
}

const cols = {
    xxl: 12,
    xl: 12
}

const breakpoints = {
    xxl: 2000,
    xl: 1200
}


const getPanels = (user, t) => {
    const tabs = []

    if (user && user.canViewUsers()) {
        tabs.push({
            menuItem: (
                <MenuItem key="user">
                    {t('crm.tab.user.name')}
                </MenuItem>),
            render: () => <UserPane />
        })
    }

    if (user && user.canViewGroups()) {
        tabs.push({
            menuItem: (
                <MenuItem key="group">
                    {t('crm.tab.group.name')}
                </MenuItem>),
            render: () => <GroupPane/>
        })
    }

    if (user && user.canViewDocuments()) {
        tabs.push({
            menuItem: (
                <MenuItem key="document">
                    {t('crm.tab.document.name')}
                </MenuItem>),
            render: () => <DocumentPane/>
        })
    }

    if (user && user.canViewTasks()) {
        tabs.push({
            menuItem: (
                <MenuItem key="task">
                    {t('crm.tab.task.name')}
                </MenuItem>),
            render: () => <TaskPane/>
        })
    }

    return tabs
}

const _Workspace = () => {

    const {user} = useContext(UserContext)
    const {t} = useTranslation()

    const panels = getPanels(user, t)


    return (
        <Suspense fallback={'Loading...'}>
            <ResponsiveGridLayout autoSize={false} cols={cols} className={'layout'} layouts={layouts} breakpoints={breakpoints}>
                <div key={"h"}>
                    <CRMHeader/>
                </div>
                <div key="s">
                    <Tab className={'tab'} menu={{secondary: true, pointing: true}} panes={panels}/>
                </div>
            </ResponsiveGridLayout>
        </Suspense>
    )

}

export const Workspace = withCurrentUser(_Workspace)