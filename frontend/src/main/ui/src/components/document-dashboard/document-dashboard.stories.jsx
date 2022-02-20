import {DocumentDashboard} from "./document-dashboard";
import React from "react";
import {DocumentDashboardContainer} from "./document-dashboard-container";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";

export default {
    title: 'Document Dashboard',
    components: [DocumentDashboard, DocumentDashboardContainer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story />
            </ApiProvider>
        )
    ]
}

const Template = args => <DocumentDashboard {...args}/>

const ContainerTemplate = args => <DocumentDashboardContainer {...args}/>

export const DefaultDocumentDashboard = Template.bind({})

DefaultDocumentDashboard.args = {
    items: [
        {id: 1, name: 'Test1', type: 'DINOSAUR_PASSPORT'},
        {id: 2, name: 'Test2', type: 'RESEARCH_DATA'}
    ]
}

export const DefaultDocumentDashboardContainer = ContainerTemplate.bind({})

