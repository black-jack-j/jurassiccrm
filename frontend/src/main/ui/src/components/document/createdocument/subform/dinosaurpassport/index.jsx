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
            <Select name={DINOSAUR_TYPE_ID} {...props[DINOSAUR_TYPE_ID]}/>
            <Input name={DINOSAUR_NAME} {...props[DINOSAUR_NAME]}/>
            <Input name={DINOSAUR_WEIGHT} {...props[DINOSAUR_WEIGHT]}/>
            <Input name={DINOSAUR_HEIGHT} {...props[DINOSAUR_HEIGHT]}/>
            <Input name={DINOSAUR_INCUBATION_DATE} {...props[DINOSAUR_INCUBATION_DATE]}/>
            <Input name={DINOSAUR_REVISION_PERIOD} {...props[DINOSAUR_REVISION_PERIOD]}/>
            <Select name={DINOSAUR_STATUS} {...props[DINOSAUR_STATUS]}/>
            <TextArea name={DINOSAUR_DESCRIPTION} {...props[DINOSAUR_DESCRIPTION]}/>
        </>
    )

}