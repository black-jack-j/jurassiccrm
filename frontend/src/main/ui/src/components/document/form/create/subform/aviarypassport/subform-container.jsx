import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../../api";
import {AVIARY_TYPE_ID} from "./fieldNames";
import {AviaryPassportSubForm} from "./index";

export const AviaryPassportSubFormContainer = props => {
    const API = useContext(ApiContext)

    const [aviaryTypes, setAviaryTypes] = useState([])
    useEffect(() => {
        API.aviaryType.getAllAviaries().then(setAviaryTypes).catch(console.error)
    }, [])

    const subformProps = {
        [AVIARY_TYPE_ID]: {
            ...props[AVIARY_TYPE_ID],
            options: aviaryTypes.map(type => ({key: type.id, text: type.name, value: type.id}))
        }
    }

    return <AviaryPassportSubForm {...subformProps}/>

}