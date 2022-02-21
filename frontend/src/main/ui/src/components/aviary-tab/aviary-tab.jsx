import {TabPane} from "semantic-ui-react";
import React from "react";
import {AviaryRevisionViewerContainer} from "../aviary-revisions-viewer/aviary-revisions-viewer";

export const AviaryPane = () => {

    return (
        <TabPane>
            <AviaryRevisionViewerContainer />
        </TabPane>
    )

}