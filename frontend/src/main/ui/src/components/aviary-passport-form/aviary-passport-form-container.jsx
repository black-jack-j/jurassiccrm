import {AVIARY_TYPE_ID} from "./fieldNames";
import React from "react";
import {AviaryPassportForm} from "./aviary-passport-form";

export const AviaryPassportFormContainer = props => {

    const {
        aviaryTypesReader,
        ...other
    } = props

    const aviaryTypes = aviaryTypesReader()

    const subformProps = {
        ...other,
        [AVIARY_TYPE_ID]: {
            ...props[AVIARY_TYPE_ID],
            options: aviaryTypes.map(type => ({key: type.id, text: type.name, value: type.id}))
        }
    }

    return (
        <AviaryPassportForm {...subformProps} />
    )

}