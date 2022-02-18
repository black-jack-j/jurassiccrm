import {CreateDocumentFormPopup} from "./create-document-form-popup";
import {Provider} from "react-redux";
import {ApiProvider} from "../../api";
import React from "react";
import {fakeAPI} from "../../fakeApi";
import {DocumentFormSelectorContainer} from "../document-form-selector/documentform-selector-container";
import store from "../../store/store";

export default {
    title: 'Create Document Popup',
    components: [CreateDocumentFormPopup],
    decorators: [
        Story => {
            return (
                <ApiProvider value={fakeAPI}>
                    <Story/>
                </ApiProvider>
            )
        }
    ]
}

const PopupWithButton = () => {

    return (
        <>
            <DocumentFormSelectorContainer/>
            <CreateDocumentFormPopup />
        </>
    )

}

const Template = args => (
    <Provider store={store}>
        <ApiProvider value={fakeAPI}>
            <PopupWithButton/>
        </ApiProvider>
    </Provider>
)

export const DefaultPopup = Template.bind({})