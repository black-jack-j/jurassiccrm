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
import {useTranslation} from "react-i18next";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../../generatedclient/apis";

export const DinosaurPassportSubForm = props => {

    const {t} = useTranslation('translation', {keyPrefix: `crm.document.form.create.${DocumentTypeEnum.DinosaurPassport}.field`})

    return (
        <>
            <Select name={DINOSAUR_TYPE_ID}
                    placeholder={t(`${DINOSAUR_TYPE_ID}.placeholder`)}
                    {...props[DINOSAUR_TYPE_ID]}/>

            <Input name={DINOSAUR_NAME}
                   placeholder={t(`${DINOSAUR_NAME}.placeholder`)}
                   {...props[DINOSAUR_NAME]}/>

            <Input name={DINOSAUR_WEIGHT}
                   placehiolder={t(`${DINOSAUR_WEIGHT}.placeholder`)}
                   {...props[DINOSAUR_WEIGHT]}/>

            <Input name={DINOSAUR_HEIGHT}
                   placeholder={t(`${DINOSAUR_HEIGHT}.placeholder`)}
                   {...props[DINOSAUR_HEIGHT]}/>

            <Input name={DINOSAUR_INCUBATION_DATE}
                   placeholder={t(`${DINOSAUR_INCUBATION_DATE}.placeholder`)}
                   {...props[DINOSAUR_INCUBATION_DATE]}/>

            <Input name={DINOSAUR_REVISION_PERIOD}
                   placeholder={t(`${DINOSAUR_REVISION_PERIOD}.placeholder`)}
                   {...props[DINOSAUR_REVISION_PERIOD]}/>

            <Select name={DINOSAUR_STATUS}
                    placeholder={t(`${DINOSAUR_STATUS}.placeholder`)}
                    {...props[DINOSAUR_STATUS]}/>

            <TextArea name={DINOSAUR_DESCRIPTION}
                      placeholder={t(`${DINOSAUR_DESCRIPTION}.placeholder`)}
                      {...props[DINOSAUR_DESCRIPTION]}/>
        </>
    )

}