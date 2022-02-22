import {TabPane} from "semantic-ui-react";
import {WidgetPane} from "../widget-pane/widget-pane";
import React from "react";
import {AviaryRevisionViewer} from "../aviary-revisions-viewer/aviary-revisions-viewer";

export const Workspace = ({children}) => (
    <TabPane>
        <WidgetPane cols={4}>
            {children}
        </WidgetPane>
    </TabPane>
)

export const WorkspaceContainer = () => {

    return (
        <Workspace>
            <AviaryRevisionViewer/>
        </Workspace>
    )

}