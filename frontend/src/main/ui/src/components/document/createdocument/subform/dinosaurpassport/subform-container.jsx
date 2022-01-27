import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../api";
import {DINOSAUR_STATUS, DINOSAUR_TYPE_ID} from "./fieldNames";
import {DinosaurPassportSubForm} from "./index";

export const DinosaurPassportSubFormContainer = props => {
    const API = useContext(ApiContext)

    const [dinosaurTypes, setDinosaurTypes] = useState([])
    const [dinosaurStatuses, setDinosaurStatuses] = useState([])

    useEffect(() => {
        API.dinosaur.getAllDinosaurTypes().then(setDinosaurTypes).catch(console.error)
        API.dinosaur.getAllDinosaurStatuses().then(setDinosaurStatuses).catch(console.error)
    }, [])

    const innerProps = {
        [DINOSAUR_TYPE_ID]: {
            ...props[DINOSAUR_TYPE_ID],
            options: dinosaurTypes.map(type => ({key: type.id, text: type.name, value: type.id}))
        },
        [DINOSAUR_STATUS]: {
            ...props[DINOSAUR_STATUS],
            options: dinosaurStatuses.map(status => ({key: status, text: status, value: status}))
        }
    }

    return <DinosaurPassportSubForm {...innerProps}/>

}