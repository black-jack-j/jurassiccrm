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
import {useTranslation} from "react-i18next";

export const ThemeZoneProjectSubform = props => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create.theme_zone_project.field'})

    return (
        <>
            <Input name={THEME_ZONE_PROJECT_NAME}
                   placeholder={t(`${THEME_ZONE_PROJECT_NAME}.placeholder`)}
                   {...props}/>

            <Input name={THEME_ZONE_PROJECT_MANAGER}
                   placeholder={t(`${THEME_ZONE_PROJECT_MANAGER}`)}
                   {...props}/>

            <FieldArray name={DINOSAURS_SELECTOR}/>
            TODO: {t(`${DINOSAURS_SELECTOR}.title`)}
            <FieldArray name={AVIARIES_SELECTOR}/>
            TODO: {t(`${AVIARIES_SELECTOR}.title`)}
            <FieldArray name={DECORATIONS_SELECTOR}/>
            TODO: {t(`${DECORATIONS_SELECTOR}.title`)}
            </>
    )

}