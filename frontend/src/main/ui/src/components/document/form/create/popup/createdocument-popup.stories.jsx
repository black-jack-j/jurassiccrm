import {CreateDocumentPopup} from "./create-document-popup";
import {Provider} from "react-redux";
import store from "../../../../../store/store";
import React from "react";
import {ApiProvider} from "../../../../../api";
import {fakeAPI} from "../../../../../fakeApi";
import {DocumentFormSelectorContainer} from "../../../documentformselector/container/documentform-selector-container";

export default {
    title: 'Create Document Popup',
    components: [CreateDocumentPopup],
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
            <CreateDocumentPopup />
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