import React from 'react'
import {AVIARY_SQUARE, AVIARY_TYPE} from "./fieldsNames";
import {Input} from "formik-semantic-ui-react";

export default ({aviaryTypeProp, squareProp, ...props}) => (

    <>
        <Input name={AVIARY_TYPE}
                  placeholder={aviaryTypeProp && aviaryTypeProp.placeholder || ''}
                  {...aviaryTypeProp}/>

        <Input name={AVIARY_SQUARE}
               placeholder={squareProp && squareProp.placeholder || ''}
               {...squareProp}/>
    </>

)