import React from 'react'
import {SemanticFormikSelectInputField} from "../../../../../utilities/SemanticUITOFormik";
import {DINOSAUR_TYPE} from "./fieldsNames";

export default (props) => <SemanticFormikSelectInputField name={DINOSAUR_TYPE} placeholder={'Тип динозавра'} {...props}/>