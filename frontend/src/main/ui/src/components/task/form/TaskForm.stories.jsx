import React from 'react'
import {CreateTaskForm} from "./create-task-form";
import {Button, Modal, ModalContent} from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css'
import {CreateTaskFormContainer} from "./create-task-form-container";
import {ApiProvider} from "../../../api";
import {fakeAPI} from "../../../fakeApi";

export default {
    title: 'Task Form',
    component: CreateTaskForm,
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
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
                <CreateTaskFormContainer onSubmit={closeModal} onClose={closeModal}/>
            </ModalContent>

        </Modal>
    )

}

export const JurassicTaskForm = () => (<ModalCreateTaskForm />)