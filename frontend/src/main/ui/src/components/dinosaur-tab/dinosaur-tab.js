import {TabPane} from "semantic-ui-react";
import {DinosaurRevisionsViewer} from "../dinosaur-revisions-viewer/dinosaur-revisions-viewer";
import React from "react";

export const DinosaurPane = () => (
    <TabPane>
        <DinosaurRevisionsViewer/>
    </TabPane>
)