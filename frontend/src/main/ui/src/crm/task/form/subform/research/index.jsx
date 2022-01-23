import React from 'react'
import {RESEARCH_GOAL} from "./fieldsNames";
import {Input} from "formik-semantic-ui-react";

export default (props) => <Input name={RESEARCH_GOAL} placeholder={'Цель исследования'} {...props}/>

export const paramsFormatter = value => ({
    [RESEARCH_GOAL]: value[RESEARCH_GOAL]
})