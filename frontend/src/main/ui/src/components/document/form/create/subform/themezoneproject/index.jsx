import {Input} from "formik-semantic-ui-react";
import {
    AVIARIES_SELECTOR,
    DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";
import React from "react";
import {FieldArray} from "formik";
import {useTranslation} from "react-i18next";
import {Container} from "../../../../../container/container";
import {FormikBasketComponent} from "../../../../../basket/formik-basket-component";
import {FormikUserSearchContainer} from "../../../../../usersearch/usersearch-component-container";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../../generatedclient/apis";

const formikBasket = Container(FormikBasketComponent)

export const ThemeZoneProjectSubform = props => {

    const {t} = useTranslation('translation', {keyPrefix: `crm.document.form.create.${DocumentTypeEnum.ThemeZoneProject}.field`})

    const dinosaurBasket = formikBasket(DINOSAURS_SELECTOR, t(`${DINOSAURS_SELECTOR}.title`), props[DINOSAURS_SELECTOR])
    const aviaryBasket = formikBasket(AVIARIES_SELECTOR, t(`${AVIARIES_SELECTOR}.title`), props[AVIARIES_SELECTOR])
    const decorationBasket = formikBasket(DECORATIONS_SELECTOR, t(`${DECORATIONS_SELECTOR}.title`), props[DECORATIONS_SELECTOR])

    return (
        <>
            <Input name={THEME_ZONE_PROJECT_NAME}
                   placeholder={t(`${THEME_ZONE_PROJECT_NAME}.placeholder`)}
                   {...props}/>

            <FormikUserSearchContainer fieldName={THEME_ZONE_PROJECT_MANAGER}
                                       placeholder={t(`${THEME_ZONE_PROJECT_MANAGER}.placeholder`)}
                                       {...props}/>

            <FieldArray name={DINOSAURS_SELECTOR} component={dinosaurBasket}/>
            <FieldArray name={AVIARIES_SELECTOR} component={aviaryBasket}/>
            <FieldArray name={DECORATIONS_SELECTOR} component={decorationBasket}/>
        </>
    )

}