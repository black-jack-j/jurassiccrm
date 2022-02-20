import {EditableDocumentCard} from "./editable-document-card";
import React from "react";

export default {
    title: 'Editable Card',
    components: [EditableDocumentCard]
}

const Template = args => <EditableDocumentCard {...args}/>

export const Default = Template.bind({})

Default.args = {
    name: 'Test',
    type: 'Research Material'
}