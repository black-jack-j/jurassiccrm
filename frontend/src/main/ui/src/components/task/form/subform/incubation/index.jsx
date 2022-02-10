import React from 'react'
import {DINOSAUR_TYPE_ID} from "./fieldsNames";
import {useTranslation} from "react-i18next";
import {TaskTOTaskTypeEnum} from "../../../../../generatedclient/models";
import {Select} from "formik-semantic-ui-react";

export const IncubationSubForm = (props) => {

    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.create.${TaskTOTaskTypeEnum.Incubation}.field`})

    return (
        <Select name={DINOSAUR_TYPE_ID}
                placeholder={t(`${DINOSAUR_TYPE_ID}.placeholder`)}
                {...props[DINOSAUR_TYPE_ID]}/>
    )
}