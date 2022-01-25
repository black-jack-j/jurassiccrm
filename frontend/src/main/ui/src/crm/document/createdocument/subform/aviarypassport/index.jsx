import {Input, Select, TextArea} from "formik-semantic-ui-react";
import React from "react";
import {
    AVIARY_BUILT_DATE,
    AVIARY_CODE,
    AVIARY_DESCRIPTION,
    AVIARY_REVISION_PERIOD,
    AVIARY_SQUARE,
    AVIARY_STATUS,
    AVIARY_TYPE_ID
} from "./fieldNames";

export const AviaryPassportSubForm = props => {

    return (
        <>
            <Select name={AVIARY_TYPE_ID} {...props}/>
            <Input name={AVIARY_SQUARE} {...props}/>
            <Input name={AVIARY_BUILT_DATE} {...props}/>
            <Input name={AVIARY_REVISION_PERIOD} {...props}/>
            <Input name={AVIARY_CODE} {...props}/>
            <Select name={AVIARY_STATUS} {...props}/>
            <TextArea name={AVIARY_DESCRIPTION} {...props}/>
        </>
    )

}