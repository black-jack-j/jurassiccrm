import {Input} from "formik-semantic-ui-react";
import {
    AVIARIES_SELECTOR,
    DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";
import React from "react";
import {FieldArray} from "formik";

export const ThemeZoneProjectSubform = props => {

    return (
        <>
            <Input name={THEME_ZONE_PROJECT_NAME} {...props}/>
            <Input name={THEME_ZONE_PROJECT_MANAGER} {...props}/>
            <FieldArray name={DINOSAURS_SELECTOR} />
            <FieldArray name={AVIARIES_SELECTOR}/>
            <FieldArray name={DECORATIONS_SELECTOR}/>
        </>
    )

}