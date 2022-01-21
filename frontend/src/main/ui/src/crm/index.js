import React from "react";
import ReactDOM from "react-dom";
import 'semantic-ui-css/semantic.min.css'
import {Tab, Grid, Button, GridColumn, Modal, ModalContent} from "semantic-ui-react";
import {CreateTaskForm} from "./task/form/CreateTaskForm";
import {TaskDashboardContainer} from "./task/dashboard/TaskDashboardContainer";
import {getAllTasks} from "./task/API";
import {TaskApi} from "jurassiccrm-client-api/dist/apis/TaskApi";

const taskAPI = new TaskApi()

const ModalCreateTaskForm = () => {

    const [open, setOpen] = React.useState(false);

    const onClose = () => setOpen(false)

    return (
        <>
            <Grid>
                <GridColumn width={6}>
                    <TaskDashboardContainer getTasks={getAllTasks}/>
                </GridColumn>
                <GridColumn width={12}>
                    <Modal open={open}
                           onClose={() => setOpen(false)}
                           onOpen={() => setOpen(true)}
                           trigger={<Button>Create Task</Button>}>

                        <ModalContent>
                            <CreateTaskForm onClose={onClose}/>
                        </ModalContent>

                    </Modal>
                </GridColumn>
            </Grid>
        </>
    )

}

const rootElement = document.getElementById("root");
ReactDOM.render(<Tab panes={[{menuitem: 'Task', render: () => <Tab.Pane><ModalCreateTaskForm /></Tab.Pane>}]}/>, rootElement);
