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
import {useTranslation} from "react-i18next";

export const AviaryPassportSubForm = props => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.form.create.aviary_passport.field'})

    return (
        <>
            <Select name={AVIARY_TYPE_ID}
                    placeholder={t(`${AVIARY_TYPE_ID}.placeholder`)}
                    {...props[AVIARY_TYPE_ID]}/>

            <Input name={AVIARY_SQUARE}
                   placeholder={t(`${AVIARY_SQUARE}.placeholder`)}
                   {...props[AVIARY_SQUARE]}/>

            <Input name={AVIARY_BUILT_DATE}
                   placeholder={t(`${AVIARY_BUILT_DATE}.placeholder`)}
                   {...props[AVIARY_BUILT_DATE]}/>

            <Input name={AVIARY_REVISION_PERIOD}
                   placeholder={t(`${AVIARY_REVISION_PERIOD}.placeholder`)}
                   {...props[AVIARY_REVISION_PERIOD]}/>

            <Input name={AVIARY_CODE}
                   placeholder={t(`${AVIARY_CODE}.placeholder`)}
                   {...props[AVIARY_CODE]}/>

            <Select name={AVIARY_STATUS}
                    placeholder={t(`${AVIARY_STATUS}.placeholder`)}
                    {...props[AVIARY_STATUS]}/>
            <TextArea name={AVIARY_DESCRIPTION}
                      placeholder={t(`${AVIARY_DESCRIPTION}.placeholder`)}
                      {...props[AVIARY_DESCRIPTION]}/>
        </>
    )

}