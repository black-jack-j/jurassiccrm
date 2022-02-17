import Popup from "reactjs-popup";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {close, open, selectNewGroupFormPopupOpened} from "./create-group-form-popup.slice";
import {SuspendableCreateGroupForm} from "../create-group-form/suspendable-create-group-form";


export const CreateGroupFormPopup = props => {

    const {
        initialValues
    } = props

    const isOpened = useSelector(selectNewGroupFormPopupOpened)
    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())
    const openPopup = () => dispatch(open())

    return (
        <Popup onClose={closePopup} onOpen={openPopup} open={isOpened} modal closeOnDocumentClick={false}>
            <SuspendableCreateGroupForm
                onSubmit={closePopup}
                onCancel={closePopup}
                initialValues={initialValues}
            />
        </Popup>
    )
}