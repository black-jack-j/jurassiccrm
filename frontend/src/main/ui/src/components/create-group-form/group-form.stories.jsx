import {GroupForm, CreateGroupFormContainer} from "./group-form";
import React from "react";
import {GROUP_MEMBERS, GROUP_PRIVILEGES} from "./fieldNames";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import {SemanticICONS} from "semantic-ui-react";

export default {
    title: 'Create User Group',
    components: [GroupForm, CreateGroupFormContainer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story />
            </ApiProvider>
        )
    ]
}

const Template = args => <GroupForm {...args}/>

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

    const initialValues = {
        [GROUP_MEMBERS]: [],
        [GROUP_PRIVILEGES]: []
    }

    return <CreateGroupFormContainer formik={initialValues}/>

}