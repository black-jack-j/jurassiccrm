import {CreateUserForm} from "./create-user-form";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";
import {UserFormInitialValues} from "../user-form/initialValues";

export default {
    title: 'Create User Form',
    components: [CreateUserForm],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

const Template = args => <CreateUserForm {...args}/>

export const Default = Template.bind({})

Default.args = {
    initialValues: UserFormInitialValues,
    onSubmit: console.log,
    onCancel: console.log
}

