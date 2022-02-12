import {Grid, GridColumn, GridRow, Tab} from "semantic-ui-react";
import React from "react";
import {LogViewerContainer} from "../logviewer/container/logviewer-container";
import {GroupsViewerContainer} from "../groups-viewer/groups-viewer";

const SecurityPanelContent = () => {
    return (
        <Grid columns={4}>
            <GridRow>
                <GridColumn>
                    <LogViewerContainer />
                </GridColumn>
                <GridColumn>
                    <GroupsViewerContainer canAdd={true} />
                </GridColumn>
            </GridRow>
        </Grid>
    )
}

export const SecurityPanel = () => <Tab.Pane><SecurityPanelContent/></Tab.Pane>