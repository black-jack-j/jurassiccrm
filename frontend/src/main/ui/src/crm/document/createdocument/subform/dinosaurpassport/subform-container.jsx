import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../api";
import {DINOSAUR_TYPE_ID} from "./fieldNames";
import {DinosaurPassportSubForm} from "./index";

export const DinosaurPassportSubFromContainer = props => {
    const API = useContext(ApiContext)

    const [dinosaurTypes, setDinosaurTypes] = useState([])
    useEffect(() => {
        API.dinosaurType.getAllDinosaurTypes().then(setDinosaurTypes).catch(console.error)
    }, [])

    const innerProps = {
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: type.id}))
        }
    }

    return <DinosaurPassportSubForm {...innerProps}/>

}