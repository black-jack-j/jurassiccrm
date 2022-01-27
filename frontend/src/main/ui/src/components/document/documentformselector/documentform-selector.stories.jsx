import {DocumentFormSelector} from "./documentform-selector";
import React from "react";
import {
    AVIARY_PASSPORT,
    DINOSAUR_PASSPORT,
    RESEARCH_MATERIAL,
    TECHNOLOGICAL_MAP,
    THEME_ZONE_PROJECT
} from "../createdocument/subform/createdocument-subform";
import {Provider} from "react-redux";
import store from "../../../store/store";

export default {
    title: 'Document Form Selector',
    components: [DocumentFormSelector],
    decorators: [
        Story => (
            <Provider store={store}>
                <Story />
            </Provider>
        )
    ]
}

const Template = args => <DocumentFormSelector {...args}/>

export const DefaultDocumentFormSelector = Template.bind({})

DefaultDocumentFormSelector.args = {
    onSubmit: console.log,
    onCancel: console.log,
    values: [DINOSAUR_PASSPORT, AVIARY_PASSPORT, RESEARCH_MATERIAL, TECHNOLOGICAL_MAP, THEME_ZONE_PROJECT]
}