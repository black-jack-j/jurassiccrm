import React from "react";
import 'semantic-ui-css/semantic.min.css'
import {Tab} from "semantic-ui-react";
import {TaskPanel} from "./task/task-panel"
import {DocumentPanel} from "./document/document-panel";

const tabs = [
    {menuItem: 'Task', render: () => <TaskPanel/>},
    {menuItem: 'Document', render: () => <DocumentPanel/>}
]

export const CRM = () => <Tab panes={tabs}/>
