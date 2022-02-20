import {AVIARIES_SELECTOR, DECORATIONS_SELECTOR, DINOSAURS_SELECTOR} from "./fieldNames";
import React from "react";
import {ThemeZoneProjectForm} from "./theme-zone-project-form";

const entityToOptionMapper = ({id, name}) => ({key: id, text: name, value: id})

export const ThemeZoneProjectFormContainer = props => {

    const {
        dinosaurTypesReader,
        aviaryTypesReader,
        decorationTypesReader,
        ...other
    } = props

    const dinosaurTypes = dinosaurTypesReader()
    const aviaryTypes = aviaryTypesReader()
    const decorationTypes = decorationTypesReader()

    const innerProps = {
        ...other,
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
                options: decorationTypes.map(entityToOptionMapper)
            }
        }
    }

    return (
        <ThemeZoneProjectForm {...innerProps}/>
    )

}