import {Input, Select, TextArea} from "formik-semantic-ui-react";
import React from "react";
import {
    DINOSAUR_DESCRIPTION,
    DINOSAUR_HEIGHT,
    DINOSAUR_INCUBATION_DATE,
    DINOSAUR_NAME,
    DINOSAUR_REVISION_PERIOD,
    DINOSAUR_STATUS,
    DINOSAUR_TYPE_ID,
    DINOSAUR_WEIGHT
} from "./fieldNames";

export const DinosaurPassportSubForm = props => {

    return (
        <>
            <Select name={DINOSAUR_TYPE_ID} {...props}/>
            <Input name={DINOSAUR_NAME} {...props}/>
            <Input name={DINOSAUR_WEIGHT} {...props}/>
            <Input name={DINOSAUR_HEIGHT} {...props}/>
            <Input name={DINOSAUR_INCUBATION_DATE} {...props}/>
            <Input name={DINOSAUR_REVISION_PERIOD} {...props}/>
            <Select name={DINOSAUR_STATUS} {...props}/>
            <TextArea name={DINOSAUR_DESCRIPTION} {...props}/>
        </>
    )

}