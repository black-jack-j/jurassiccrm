import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../../../api";
import {ThemeZoneProjectSubform} from "./index";
import {AVIARIES_SELECTOR, DECORATIONS_SELECTOR, DINOSAURS_SELECTOR} from "./fieldNames";

const entityToOptionMapper = ({id, name}) => ({key: id, text: name, value: id})

const BASKET_TRANSFORMER = values => values.reduce((acc, val) => {
    acc[val.item] = val.count
    return acc
}, {})

export const THEME_ZONE_FORMIK_TRANSFORMER = values => ({
    ...values,
    [DINOSAURS_SELECTOR]: BASKET_TRANSFORMER(values[DINOSAURS_SELECTOR]),
    [AVIARIES_SELECTOR]: BASKET_TRANSFORMER(values[AVIARIES_SELECTOR]),
    [DECORATIONS_SELECTOR]: BASKET_TRANSFORMER(values[DECORATIONS_SELECTOR]),
})

export const ThemeZoneProjectSubFormContainer = props => {
    const API = useContext(ApiContext)

    const [dinosaurTypes, setDinosaurTypes] = useState([])
    const [aviaryTypes, setAviaryTypes] = useState([])
    const [decorationType, setDecorationTypes] = useState([])

    useEffect(() => {
        API.dinosaur.getAllDinosaurTypes().then(setDinosaurTypes)
        API.aviary.getAllAviaryTypes().then(setAviaryTypes)
        API.decorationType.getAllDecorations().then(setDecorationTypes)
    }, [])

    const innerProps = {
        [DINOSAURS_SELECTOR]: {
            ...props[DINOSAURS_SELECTOR],
            item: {
                options: dinosaurTypes.map(entityToOptionMapper)
            }
        },
        [AVIARIES_SELECTOR]: {
            ...props[AVIARIES_SELECTOR],
            item: {
                options: aviaryTypes.map(entityToOptionMapper)
            }
        },
        [DECORATIONS_SELECTOR]: {
            ...props[DECORATIONS_SELECTOR],
            item: {
                options: decorationType.map(entityToOptionMapper)
            }
        }
    }

    return <ThemeZoneProjectSubform {...innerProps}/>
}