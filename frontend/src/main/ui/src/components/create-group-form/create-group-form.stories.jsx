import {CreateGroupForm} from "./create-group-form";
import React from "react";
import {GROUP_MEMBERS, GROUP_PRIVILEGES} from "./fieldNames";

export default {
    title: 'Create User Group',
    components: [CreateGroupForm]
}

const Template = args => <CreateGroupForm {...args}/>

export const Empty = Template.bind({})

Empty.args = {
    formik: {
        initialValues: {
            [GROUP_MEMBERS]: [],
            [GROUP_PRIVILEGES]: []
        }
    }
}