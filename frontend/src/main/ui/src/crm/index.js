import React from "react";
import ReactDOM from "react-dom";
import 'semantic-ui-css/semantic.min.css'
import {Button, Grid, GridColumn, Modal, ModalContent, Tab} from "semantic-ui-react";
import {CreateTaskForm} from "./task/form/CreateTaskForm";
import {TaskDashboardContainer} from "./task/dashboard/taskdashboard-container";
import {Viewer} from "./task/viewer/viewer";

const ModalCreateTaskForm = () => {

    const [state, setState] = React.useState({task: undefined, open: false});

    const toggleModalOpened = (opened) => setState({...state, open: opened})
    const onClose = () => toggleModalOpened(false)
    const onOpen = () => toggleModalOpened(true)
    const onTaskSelected = task => {
        console.log("selected")
        console.log(task)
        setState({...state, task: {...task}})
    }

    return (
        <>
            <Grid>
                <GridColumn width={6}>
                    <TaskDashboardContainer onTaskSelected={onTaskSelected}/>
                </GridColumn>
                <GridColumn width={12}>
                    <Modal open={state.open}
                           onClose={onClose}
                           onOpen={onOpen}
                           trigger={<Button>Create Task</Button>}>

                        <ModalContent>
                            <CreateTaskForm onClose={onClose}/>
                        </ModalContent>

                    </Modal>
                    <Viewer task={state.task}/>
                </GridColumn>
            </Grid>
        </>
    )

}

const rootElement = document.getElementById("root");
ReactDOM.render(<Tab panes={[{menuitem: 'Task', render: () => <Tab.Pane><ModalCreateTaskForm /></Tab.Pane>}]}/>, rootElement);
