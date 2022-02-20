import {UpdateDocumentFormPopup} from "./update-document-form-popup";
import {Provider, useDispatch} from "react-redux";
import {ApiProvider} from "../../api";
import React from "react";
import {fakeAPI} from "../../fakeApi";
import store from "../../store/store";
import {Button} from "semantic-ui-react";
import {updateDocument} from "./update-document-form-popup-slice";

export default {
    title: 'Update Document Popup',
    components: [UpdateDocumentFormPopup],
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

const PopupWithButton = ({id, documentType}) => {

    const dispatch = useDispatch()
    const handleClick = () => dispatch(updateDocument({id, documentType}))

    return (
        <>
            <Button type={'button'} onClick={handleClick}>Update document {id} of type {documentType}</Button>
            <UpdateDocumentFormPopup />
        </>
    )

}

const Template = args => {

    const {id, documentType} = args

    return (
        <Provider store={store}>
            <ApiProvider value={fakeAPI}>
                <PopupWithButton id={id} documentType={documentType}/>
            </ApiProvider>
        </Provider>
    )
}

export const DefaultPopup = Template.bind({})

DefaultPopup.args = {
    id: 0,
    documentType: 'AVIARY_PASSPORT'
}