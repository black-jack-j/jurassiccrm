import {EntitySelector} from "./entity-selector";
import withFormik from "storybook-formik";
import React from "react";
import _ from "lodash";

export default {
    title: 'Group Members Selector',
    components: [EntitySelector],
    decorators: [
        withFormik
    ]
}

const Template = args => <EntitySelector {...args}/>

export const Default = Template.bind({})

const getOptions = (n=1) => _.range(0, n).map(i => ({id: i, value: i, text: i}))

Default.args = {
    name: 'members',
    options: getOptions(10)
}

Default.parameters = {
    formik: {
        initialValues: {
            members: []
        }
    }
}

export const WithPredefinedItems = Template.bind({})

WithPredefinedItems.args = {
    name: 'members',
    options: getOptions(10)
}

WithPredefinedItems.parameters = {
    formik: {
        initialValues: {
            members: [0, 1, 5]
        }
    }
}