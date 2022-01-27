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
            <Select name={AVIARY_TYPE_ID} {...props[AVIARY_TYPE_ID]}/>
            <Input name={AVIARY_SQUARE} {...props[AVIARY_SQUARE]}/>
            <Input name={AVIARY_BUILT_DATE} {...props[AVIARY_BUILT_DATE]}/>
            <Input name={AVIARY_REVISION_PERIOD} {...props[AVIARY_REVISION_PERIOD]}/>
            <Input name={AVIARY_CODE} {...props[AVIARY_CODE]}/>
            <Select name={AVIARY_STATUS} {...props[AVIARY_STATUS]}/>
            <TextArea name={AVIARY_DESCRIPTION} {...props[AVIARY_DESCRIPTION]}/>
        </>
    )

}