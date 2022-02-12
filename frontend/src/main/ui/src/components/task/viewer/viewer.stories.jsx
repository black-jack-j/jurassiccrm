import {Viewer} from "./viewer";
import React from "react";
import {INCUBATION_TYPE} from "../form/subform/subform";


export default {
    title: 'Viewer',
    components: [Viewer]
}

const Template = args => <Viewer {...args}/>

export const DefaultViewer = Template.bind({})

DefaultViewer.args = {
    name: 'Test',
    taskType: INCUBATION_TYPE,
    created: Instant.now().toEpochMilli(),
    lastUpdated: Instant.now().toEpochMilli(),
    currentState: 'OPEN',
    createdById: 666,
    assigneeId: 42,
    lastUpdaterId: 13
}