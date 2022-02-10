import {DocumentFormSelectorContainer} from "./documentform-selector-container";
import {ApiProvider} from "../../../../api";
import React from "react";
import {Provider} from "react-redux";
import store from "../../../../store/store";
import {fakeAPI} from "../../../../fakeApi";

export default {
    title: 'DocumentFormSelectorContainer',
    components: [DocumentFormSelectorContainer]
}
const Template = args => <DocumentFormSelectorContainer {...args}/>

export const DefaultDocumentFormSelectorContainer = Template.bind({})

DefaultDocumentFormSelectorContainer.args = {
    onSubmit: console.log,
    onCancel: console.log
}

DefaultDocumentFormSelectorContainer.decorators = [
    Story => (
        <Provider store={store}>
            <ApiProvider value={fakeAPI}>
                <Story />
            </ApiProvider>
        </Provider>
    )
]