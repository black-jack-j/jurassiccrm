import {UserForm} from "./user-form";
import React from "react";
import {UserFormInitialValues} from "./initialValues";

export default {
    title: 'UserForm',
    components: [UserForm]
}

const Template = args => <UserForm {...args}/>

export const Default = Template.bind({})

Default.args = {
    initialValues: UserFormInitialValues,
    onSubmit: console.log,
    onCancel: console.log
}