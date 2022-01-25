import React from "react";
import {FieldArray} from "formik";
import {DINOSAUR_EGG_CREATION_STEPS, DINOSAUR_INCUBATION_STEPS, DINOSAUR_TYPE_NAME} from "./fieldNames";
import {Input, Select} from "formik-semantic-ui-react";
import {Container} from "../../../../components/container/container";

const TextInputSteps = ({fieldName, ...props}) => {
    return <Input name={fieldName} {...props}/>
}

const TextInputStepsContainer = Container(TextInputSteps)

export const TechnologicalMapSubform = props => {

    return (
        <>
            <Select name={DINOSAUR_TYPE_NAME} placeholder={'Тип динозавра'} options={[]}/>
            <FieldArray name={DINOSAUR_INCUBATION_STEPS}
                        component={TextInputStepsContainer(DINOSAUR_INCUBATION_STEPS, 'Шаги создания яйца')}/>

            <FieldArray name={DINOSAUR_EGG_CREATION_STEPS}
                        component={TextInputStepsContainer(DINOSAUR_EGG_CREATION_STEPS, 'Шаги инкубации яйца')}/>
        </>
    )

}