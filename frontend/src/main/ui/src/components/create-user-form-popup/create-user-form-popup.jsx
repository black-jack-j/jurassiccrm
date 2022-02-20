import {useDispatch, useSelector} from "react-redux";
import {close, selectNewUserFormPopupOpened} from "./create-user-form-popup-slice";
import {useTranslation} from "react-i18next";
import {ModalContent, Segment} from "semantic-ui-react";
import React from "react";
import {CreateUserFormContainer} from "../create-user-form/create-user-form-container";
import Popup from "reactjs-popup";

export const CreateUserFormPopup = ({initialValues}) => {

    const open = useSelector(selectNewUserFormPopupOpened)

    const dispatch = useDispatch()
    const {t} = useTranslation()

    const closePopup = () => dispatch(close())

    return (
        <Popup open={open} onClose={() => closePopup()} modal nested>
            <Segment style={{width: "600px"}}>
                {open && <CreateUserFormContainer onSubmit={closePopup}
                                                  onCancel={closePopup} initialValues={initialValues}/>}
            </Segment>
        </Popup>
    )

}