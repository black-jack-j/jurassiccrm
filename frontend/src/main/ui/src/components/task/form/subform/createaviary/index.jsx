import React from 'react'
import {AVIARY_SQUARE, AVIARY_TYPE_ID} from "./fieldsNames";
import {Input, Select} from "formik-semantic-ui-react";
import {useTranslation} from "react-i18next";
import {TaskTOTaskTypeEnum} from "../../../../../generatedclient/models";

export const AviarySubForm = props => {
    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.create.${TaskTOTaskTypeEnum.AviaryCreation}.field`})

    return (
        <>
            <Select name={AVIARY_TYPE_ID}
                    placeholder={t(`${AVIARY_TYPE_ID}.placeholder`)} {...props[AVIARY_TYPE_ID]}/>

            <Input name={AVIARY_SQUARE}
                   placeholder={t(`${AVIARY_SQUARE}.placeholder`)} {...props[AVIARY_SQUARE]}/>
        </>
    )
}

export const paramsFormatter = value => ({
    [AVIARY_TYPE_ID]: typeof value[AVIARY_TYPE_ID] === 'undefined' ? null : Number(value[AVIARY_TYPE_ID]),
    [AVIARY_SQUARE]: Number(value[AVIARY_SQUARE])
})