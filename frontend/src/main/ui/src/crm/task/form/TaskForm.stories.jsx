import React from 'react'
import {CreateTaskForm} from "./create-task-form";
import {Button, Modal, ModalContent} from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css'

export default {
    title: 'Task Form',
    component: CreateTaskForm
}

const ModalCreateTaskForm = () => {

    const [open, setOpen] = React.useState(false);

    const closeModal = () => setOpen(false)

    return (
        <Modal
               open={open}
               onClose={closeModal}
               onOpen={() => setOpen(true)}
               trigger={<Button>Create Task</Button>}>

            <ModalContent>
                <CreateTaskForm onClose={closeModal}/>
            </ModalContent>

        </Modal>
    )

}

export const JurassicTaskForm = () => (<ModalCreateTaskForm />)