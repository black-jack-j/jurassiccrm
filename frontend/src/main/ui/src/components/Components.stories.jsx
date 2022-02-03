import React from 'react'
import {UserSearchContainer} from "./usersearch/usersearch-component-container";
import {ApiProvider} from "../api";
import {fakeAPI} from "../fakeApi";
import withFormik from "storybook-formik";

export default {
    title: 'Components',
    components: [UserSearchContainer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        ),
        withFormik
    ]
}

const Template = args => <UserSearchContainer {...args}/>

export const UserSearch = Template.bind({})

UserSearch.args = {
    fieldName: 'user'
}

UserSearch.parameters = {
    formik: {
        initialValues: {
            user: undefined
        }
    }
}
