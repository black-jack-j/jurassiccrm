import {Container} from "./container";
import React from "react";
import {FieldArray} from "formik";
import withFormik from "storybook-formik";
import {Input} from "formik-semantic-ui-react";

import i18n from '../../i18n'
import {I18nextProvider} from "react-i18next";

export default {
    title: 'Container',
    components: [Container],
    decorators: [
        withFormik,
        Story => (
            <I18nextProvider i18n={i18n}>
                <Story />
            </I18nextProvider>
        )
    ]
}

const TestComponent = () => <div>Item</div>

const InputComponent = ({fieldName, ...props}) => <Input name={fieldName} {...props} />

const TestContainer = Container(TestComponent)( 'test', 'Test')

const TestInputContainer = Container(InputComponent)('input_test', 'crm.test')

const Template = args => <FieldArray {...args}/>

export const DefaultContainer = Template.bind({})

DefaultContainer.args = {
    name: 'test',
    component: TestContainer
}

DefaultContainer.parameters = {
    formik: {
        initialValues: {
            test: []
        }
    }
}

export const DefaultInputContainer = Template.bind({})
DefaultInputContainer.args = {
    name: 'input_test',
    component: TestInputContainer
}

DefaultInputContainer.parameters = {
    formik: {
        initialValues: {
            input_test: []
        }
    }
}
