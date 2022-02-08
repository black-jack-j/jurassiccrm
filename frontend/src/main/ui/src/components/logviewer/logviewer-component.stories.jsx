import {LogViewerComponent} from "./logviewer-component";
import React, {Suspense} from "react";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";

export default {
    title: 'LogViewer',
    components: [LogViewerComponent],
    decorators: [
        Story => (
            <Suspense fallback={<div>Loading</div>}>
                <ApiProvider value={fakeAPI}>
                    <Story/>
                </ApiProvider>
            </Suspense>
        )
    ],
    parameters: {
        docs: {
            source: {
                code: 'Disabled'
            }
        }
    }
}

const Template = args => (
    <LogViewerComponent {...args}/>
)

export const EmptyLogViewer = Template.bind({})

EmptyLogViewer.args = {
    items: []
}

const items = [
    {username: 'ATest', action: 'Add dinosaur passport', timestamp: new Date()},
    {username: 'BTest', action: 'Add aviary passport', timestamp: new Date()}
]

export const FilledLogViewer = Template.bind({})
FilledLogViewer.args = {
    items
}