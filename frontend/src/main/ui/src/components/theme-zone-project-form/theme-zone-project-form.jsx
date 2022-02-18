import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import {DOCUMENT_NAME} from "../document/form/create/fieldNames";
import {Menu, MenuItem} from "semantic-ui-react";
import {FieldArray, Formik} from "formik";
import React from "react";
import {
    AVIARIES_SELECTOR, DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "../document/form/create/subform/themezoneproject/fieldNames";
import {ThemeZoneProjectManagerSearchComponent} from "../projectmanagersearch/theme-zone-project-manager-search-component";
import {Container} from "../container/container";
import {FormikBasketComponent} from "../basket/formik-basket-component";

const formikBasket = Container(FormikBasketComponent)

export const ThemeZoneProjectForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        translations
    } = props

    const dinosaurBasket = formikBasket(DINOSAURS_SELECTOR, translations(`field.${DINOSAURS_SELECTOR}.title`), props[DINOSAURS_SELECTOR])
    const aviaryBasket = formikBasket(AVIARIES_SELECTOR, translations(`field.${AVIARIES_SELECTOR}.title`), props[AVIARIES_SELECTOR])
    const decorationBasket = formikBasket(DECORATIONS_SELECTOR, translations(`field.${DECORATIONS_SELECTOR}.title`), props[DECORATIONS_SELECTOR])

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}>
            <Form>
                <Input name={DOCUMENT_NAME} placeholder={translations(`field.${DOCUMENT_NAME}.placeholder`)}/>
                <Input name={THEME_ZONE_PROJECT_NAME}
                       placeholder={translations(`field.${THEME_ZONE_PROJECT_NAME}.placeholder`)}
                       {...props}/>

                <ThemeZoneProjectManagerSearchComponent name={THEME_ZONE_PROJECT_MANAGER}
                                                        placeholder={translations(`field.${THEME_ZONE_PROJECT_MANAGER}.placeholder`)}
                                                        {...props}/>


                <FieldArray name={DINOSAURS_SELECTOR} component={dinosaurBasket}/>
                <FieldArray name={AVIARIES_SELECTOR} component={aviaryBasket}/>
                <FieldArray name={DECORATIONS_SELECTOR} component={decorationBasket}/>
                <Menu secondary>
                    <MenuItem>
                        <SubmitButton floated={'left'} positive>{translations('submit')}</SubmitButton>
                    </MenuItem>
                    <MenuItem position={'right'}>
                        <ResetButton floated={'right'} negative onClick={onCancel}>{translations('cancel')}</ResetButton>
                    </MenuItem>
                </Menu>
            </Form>
        </Formik>
    )

}