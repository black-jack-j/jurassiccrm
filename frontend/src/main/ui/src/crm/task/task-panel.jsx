import {useDispatch} from "react-redux";
import {Button, Grid, GridColumn, Tab} from "semantic-ui-react";
import {TaskDashboardContainer} from "./dashboard/taskdashboard-container";
import {open} from "./form/create-task-popup-slice";
import {CreateTaskPopup} from "./form/create-task-popup";
import {EditTaskPopup} from "./editor/edit-task-popup";
import {Viewer} from "./viewer/viewer";
import React from "react";

const TaskPanelContent = () => {

    const dispatch = useDispatch()

    return (
        <>
            <Grid>
                <GridColumn width={6}>
                    <TaskDashboardContainer/>
                </GridColumn>
                <GridColumn width={12}>
                    <Button onClick={() => dispatch(open())}>Create</Button>
                    <CreateTaskPopup />
                    <EditTaskPopup />
                    <Viewer />
                </GridColumn>
            </Grid>
        </>
    )

}


export const TaskPanel = () => <Tab.Pane><TaskPanelContent/></Tab.Pane>