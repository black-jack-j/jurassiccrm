import {Workspace} from "./workspace";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";
import {AviaryRevisionViewer} from "../aviary-revisions-viewer/aviary-revisions-viewer";

export default {
    title: 'Workspace',
    components: [Workspace],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

export const EmptyWorkspace = () => (
    <Workspace>
    </Workspace>
)

export const SingleWidgetWorkspace = () => (
    <Workspace>
        <AviaryRevisionViewer/>
    </Workspace>
)

export const TwoWidgetWorkspace = () => (
    <Workspace>
        <AviaryRevisionViewer/>
        <AviaryRevisionViewer/>
    </Workspace>
)