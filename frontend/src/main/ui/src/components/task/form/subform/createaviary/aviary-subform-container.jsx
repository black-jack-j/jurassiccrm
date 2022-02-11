import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../api";
import {AviarySubForm} from "./index";
import {AVIARY_TYPE_ID} from "./fieldsNames";

export const AviarySubFormContainer = ({...props}) => {

    const [aviaryTypes, setAviaryTypes] = useState([])

    const API = useContext(ApiContext)

    useEffect(() => {
        API.aviary.getAllAviaryTypes().then(setAviaryTypes).catch(console.error)
    }, [])

    const initialProps = {
        [AVIARY_TYPE_ID]: {
            ...props[AVIARY_TYPE_ID],
            options: aviaryTypes.map(type => ({key: type.id, value: type.id, text: type.name}))
        }
    }

    return <AviarySubForm {...initialProps}/>

}