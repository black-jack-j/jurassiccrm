import React from "react";
import {FieldArray} from "formik";
import {DINOSAUR_EGG_CREATION_STEPS, DINOSAUR_INCUBATION_STEPS, DINOSAUR_TYPE_NAME} from "./fieldNames";
import {Input, Select} from "formik-semantic-ui-react";
import {Container} from "../../../../../container/container";
import {useTranslation} from "react-i18next";

const TextInputSteps = ({fieldName, ...props}) => {
    return <Input name={fieldName} {...props}/>
}

const TextInputStepsContainer = Container(TextInputSteps)

export const TechnologicalMapSubform = props => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create.technological_map.field'})

    const IncubationStepsField = TextInputStepsContainer(DINOSAUR_INCUBATION_STEPS, t(`${DINOSAUR_INCUBATION_STEPS}.title`))

    const EggCreationStepsField = TextInputStepsContainer(DINOSAUR_EGG_CREATION_STEPS, t(`${DINOSAUR_EGG_CREATION_STEPS}.title`))

    return (
        <>
            <Select name={DINOSAUR_TYPE_NAME}
                    placeholder={t(`${DINOSAUR_TYPE_NAME}.placeholder`)} options={[]}/>

            <FieldArray name={DINOSAUR_EGG_CREATION_STEPS}
                        component={EggCreationStepsField}/>

            <FieldArray name={DINOSAUR_INCUBATION_STEPS}
                        component={IncubationStepsField}/>


        </>
    )

}