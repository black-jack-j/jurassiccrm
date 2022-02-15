import Popup from "reactjs-popup";
import {CreateGroupForm} from "../create-group-form/create-group-form";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {selectNewGroupFormPopupOpened, close, open} from "./create-group-form-popup.slice";

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
            <CreateGroupForm onSubmit={closePopup} onCancel={closePopup} initialValues={initialValues}/>
        </Popup>
    )
}