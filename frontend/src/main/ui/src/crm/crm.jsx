import React from "react";
import 'semantic-ui-css/semantic.min.css'
import {Button, Grid, GridColumn, Tab} from "semantic-ui-react";
import {TaskDashboardContainer} from "./task/dashboard/taskdashboard-container";
import {Viewer} from "./task/viewer/viewer";
import {CreateTaskPopup} from "./task/form/create-task-popup";
import {open} from "./task/form/create-task-popup-slice";
import {useDispatch} from "react-redux";
import {EditTaskPopup} from "./task/editor/edit-task-popup";

const TaskPanel = () => {

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


export const CRM = () => <Tab panes={[{menuitem: 'Task', render: () => <Tab.Pane><TaskPanel/></Tab.Pane>}]}/>
