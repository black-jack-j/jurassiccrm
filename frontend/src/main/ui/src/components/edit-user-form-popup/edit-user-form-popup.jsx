import {useDispatch, useSelector} from "react-redux";
import Popup from "reactjs-popup";
import {Segment} from "semantic-ui-react";
import React from "react";
import {close, selectEditUserFormPopupOpened, selectEditUserFormPopupUserId} from "./edit-user-form-popup.slice";
import {SuspendableEditUserForm} from "../edit-user-form/suspendable-edit-user-form";

export const EditUserFormPopup = () => {

    const open = useSelector(selectEditUserFormPopupOpened)
    const userId = useSelector(selectEditUserFormPopupUserId)

    const dispatch = useDispatch()

    const closePopup = () => dispatch(close())

    return (
        <Popup open={open} onClose={() => closePopup()} modal nested>
            <Segment style={{width: "600px"}}>
                {userId &&
                    <SuspendableEditUserForm
                        onSubmit={closePopup}
                        onCancel={closePopup}
                        userId={userId}
                    />
                }
            </Segment>
        </Popup>
    )

}