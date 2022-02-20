import {DocumentFormSelector} from "./documentform-selector";
import React from "react";
import {Provider} from "react-redux";
import store from "../../store/store";
import {CreateDocumentDocumentTypeEnum} from "../../generatedclient/apis";

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
    values: [
        CreateDocumentDocumentTypeEnum.DinosaurPassport,
        CreateDocumentDocumentTypeEnum.AviaryPassport,
        CreateDocumentDocumentTypeEnum.ResearchData,
        CreateDocumentDocumentTypeEnum.TechnologicalMap,
        CreateDocumentDocumentTypeEnum.ThemeZoneProject
    ]
}