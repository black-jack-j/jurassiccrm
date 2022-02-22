import {TabPane} from "semantic-ui-react";
import React from "react";
import {AviaryRevisionViewer} from "../aviary-revisions-viewer/aviary-revisions-viewer";

export const AviaryPane = () => {

    return (
        <TabPane>
            <AviaryRevisionViewer />
        </TabPane>
    )

}