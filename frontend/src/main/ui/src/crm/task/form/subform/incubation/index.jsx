import React from 'react'
import {SemanticFormikSelectInputField} from "../../../../../utilities/SemanticUITOFormik";
import {DINOSAUR_TYPE_ID} from "./fieldsNames";

export default (props) => <SemanticFormikSelectInputField name={DINOSAUR_TYPE_ID} placeholder={'Тип динозавра'} {...props}/>

export const paramsFormatter = value => ({
    [DINOSAUR_TYPE_ID]: typeof value[DINOSAUR_TYPE_ID] === 'undefined' ? null : Number(value[DINOSAUR_TYPE_ID])
})