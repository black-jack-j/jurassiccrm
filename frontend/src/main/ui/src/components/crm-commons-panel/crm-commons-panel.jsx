import {Tab, TabPane} from "semantic-ui-react";
import {TaskDashboardContainer} from "../task/dashboard/taskdashboard-container";
import {DocumentDashboardContainer} from "../document/dashboard/document-dashboard-container";
import React from "react";

const TaskPane = () => {

    return (
        <TabPane className={'pane'} attached={false}>
            <TaskDashboardContainer />
        </TabPane>
    )

}

const DocumentPane = () => {

    return (
        <TabPane className={'pane'}  attached={false}>
            <DocumentDashboardContainer />
        </TabPane>
    )

}

const taskDocumentsPanes = [
    {menuItem: 'Tasks', render: () => <TaskPane/>},
    {menuItem: 'Documents', render: () => <DocumentPane />}
]

export const CRMCommonsPanel = props => {

    return <Tab className={'tab'} menu={{secondary: true, pointing: true}} panes={taskDocumentsPanes}/>

}