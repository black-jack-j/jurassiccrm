import Popup from "reactjs-popup";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {
    close,
    open,
    selectUpdateGroupFormPopupGroupId as selectGroupId,
    selectUpdateGroupFormPopupOpened as selectOpened
} from "./update-group-form-popup.slice";
import {EditGroupForm} from "../edit-group-form/edit-group-form";

export const EditGroupFormPopup = props => {

    const {
        Placeholder,
        LoadingPlaceholder,
        ErrorPlaceholder
    } = props

    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())
    const openPopup = () => dispatch(open())

    const isOpened = useSelector(selectOpened)
    const groupId = useSelector(selectGroupId)

    return (
        <Popup onClose={closePopup} onOpen={openPopup} open={isOpened} modal closeOnDocumentClick={false}>
            {groupId ?
                <EditGroupForm
                    id={groupId}
                    onSubmit={closePopup}
                    onCancel={closePopup}
                    LoadingPlaceholder={LoadingPlaceholder}
                    ErrorPlaceholder={ErrorPlaceholder}
                /> :
                (Placeholder && <Placeholder />)
            }
        </Popup>
    )
}