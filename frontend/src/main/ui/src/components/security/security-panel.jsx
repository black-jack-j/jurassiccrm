import {Tab} from "semantic-ui-react";
import React from "react";
import {LogViewerContainer} from "../logviewer/container/logviewer-container";

const SecurityPanelContent = () => {
    return <LogViewerContainer/>
}

export const SecurityPanel = () => <Tab.Pane><SecurityPanelContent/></Tab.Pane>