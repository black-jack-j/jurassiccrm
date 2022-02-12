import {CreateGroupForm, CreateGroupFormContainer} from "./create-group-form";
import React from "react";
import {GROUP_MEMBERS, GROUP_PRIVILEGES} from "./fieldNames";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";

export default {
    title: 'Create User Group',
    components: [CreateGroupForm, CreateGroupFormContainer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story />
            </ApiProvider>
        )
    ]
}

const Template = args => <CreateGroupForm {...args}/>

export const Empty = Template.bind({})

Empty.args = {
    [GROUP_MEMBERS]: {
        options: []
    },
    [GROUP_PRIVILEGES]: {
        options: []
    },
    formik: {
        initialValues: {
            [GROUP_MEMBERS]: [],
            [GROUP_PRIVILEGES]: []
        }
    }
}

export const Interactive = () => {

    const formik = {
        initialValues: {
            [GROUP_MEMBERS]: [],
            [GROUP_PRIVILEGES]: []
        }
    }

    return <CreateGroupFormContainer formik={formik}/>

}