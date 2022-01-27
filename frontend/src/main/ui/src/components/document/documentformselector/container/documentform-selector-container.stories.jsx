import {DocumentFormSelectorContainer} from "./documentform-selector-container";
import {ApiProvider} from "../../../../api";
import {UserRolesEnum} from "../../../../generatedclient/models";
import React, {Suspense} from "react";
import {Provider} from "react-redux";
import store from "../../../../store/store";

const mockApi = {
    user: {
        getCurrentUserRoles: async () => [UserRolesEnum.ThemeZoneProjectWriter, UserRolesEnum.DinosaurPassportWriter]
    }
}

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
            <ApiProvider value={mockApi}>
                <Story />
            </ApiProvider>
        </Provider>
    )
]