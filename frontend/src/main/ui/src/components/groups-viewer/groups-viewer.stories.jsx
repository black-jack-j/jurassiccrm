import {GroupsViewer} from "./groups-viewer";
import React from "react";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";

export default {
    title: 'Groups Viewer',
    components: [GroupsViewer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

const Template = args => <GroupsViewer {...args}/>

export const Empty = Template.bind({})

export const MultipleElements = Template.bind({})

MultipleElements.args = {
    groups: [
        {id: 1, name: 'Group1', description: 'SOme description', users: [], roles: []},
        {id: 2, name: 'Group2', description: 'SOme description', users: [], roles: []},
        {id: 3, name: 'Group3', description: 'SOme description', users: [], roles: []},
        {id: 4, name: 'Group4', description: 'SOme description', users: [], roles: []},
        {id: 5, name: 'Group5', description: 'SOme description', users: [], roles: []},
    ],
    canAdd: true
}