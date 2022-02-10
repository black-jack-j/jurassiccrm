import React from 'react'
import {RESEARCH_GOAL} from "./fieldsNames";
import {Input} from "formik-semantic-ui-react";
import {useTranslation} from "react-i18next";
import {TaskTOTaskTypeEnum} from "../../../../../generatedclient/models";

export const ResearchSubForm = (props) => {

    const {t} = useTranslation('translation', {keyPrefix: `crm.task.form.create.${TaskTOTaskTypeEnum.Research}.field`})

    return <Input name={RESEARCH_GOAL} placeholder={t(`${RESEARCH_GOAL}.placeholder`)} {...props[RESEARCH_GOAL]}/>
}