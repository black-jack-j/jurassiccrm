import React, {Suspense} from "react";
import {ApiProvider} from "../../../api";
import {fakeAPI} from "../../../fakeApi";
import {LogViewerContainer} from "./logviewer-container";

export default {
    title: 'LogViewerContainer',
    components: [LogViewerContainer],
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
    <LogViewerContainer {...args}/>
)

export const EmptyLogViewer = Template.bind({})