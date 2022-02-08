import React from 'react'
import {AVIARY_SQUARE, AVIARY_TYPE_ID} from "./fieldsNames";
import {Input} from "formik-semantic-ui-react";

export default props => (

    <>
        <Input name={AVIARY_TYPE_ID}
               placeholder={'Тип вольера'} {...props}/>

        <Input name={AVIARY_SQUARE}
               placeholder={'Площадь вольера'} {...props}/>
    </>

)

export const paramsFormatter = value => ({
    [AVIARY_TYPE_ID]: typeof value[AVIARY_TYPE_ID] === 'undefined' ? null : Number(value[AVIARY_TYPE_ID]),
    [AVIARY_SQUARE]: Number(value[AVIARY_SQUARE])
})