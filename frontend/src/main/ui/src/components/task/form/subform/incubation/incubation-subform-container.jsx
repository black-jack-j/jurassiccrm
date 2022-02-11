import {IncubationSubForm} from "./index";
import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../api";
import {DINOSAUR_TYPE_ID} from "./fieldsNames";

export const IncubationSubFormContainer = ({...props}) => {

    const [dinosaurs, setDinosaurs] = useState([])

    const API = useContext(ApiContext)

    useEffect(() => {
        API.dinosaur.getAllDinosaurTypes().then(setDinosaurs).catch(console.error)
    }, [])

    const initialProps = {
        [DINOSAUR_TYPE_ID]: {
            options: dinosaurs.map(type => ({key: type.id, text: type.name, value: type.id}))
        }
    }

    return <IncubationSubForm {...initialProps}/>

}