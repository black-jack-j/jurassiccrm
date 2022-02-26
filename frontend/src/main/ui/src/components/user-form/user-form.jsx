import {Formik} from "formik";
import {Form, Input} from "formik-semantic-ui-react";
import React from "react";
import {Button, Grid, GridColumn, GridRow, Header, SegmentInline} from "semantic-ui-react";
import {
    USER_DEPARTMENT,
    USER_FIRSTNAME,
    USER_GROUPS,
    USER_LASTNAME,
    USER_PASSWORD,
    USER_PASSWORD_CHECK,
    USER_USERNAME
} from "./fieldNames";
import {EntitySelector} from "../entity-selector/entity-selector";
import {FormikSelect} from "../formik-select/formik-select";

export const UserForm = props => {

    const {
        initialValues,
        onSubmit,
        onCancel,
        translations = key => key
    } = props

    return (
        <>
            <Header>{translations('title')}</Header>
            <Formik initialValues={initialValues} onSubmit={(values, {resetForm}) => {
                onSubmit && onSubmit(values)
                resetForm()
            }} onReset={onCancel}>
                <Form>
                    <Grid columns={2}>
                        <GridColumn width={10} stretched>
                            <Input name={USER_FIRSTNAME}
                                   placeholder={translations(`field.${USER_FIRSTNAME}.placeholder`)}
                                   {...props[USER_FIRSTNAME]}
                            />

                            <Input name={USER_LASTNAME}
                                   placeholder={translations(`field.${USER_LASTNAME}.placeholder`)}
                                   {...props[USER_LASTNAME]}
                            />
                            <FormikSelect name={USER_DEPARTMENT}
                                    placeholder={translations(`field.${USER_DEPARTMENT}.placeholder`)}
                                    {...props[USER_DEPARTMENT]}/>

                            <Input name={USER_USERNAME}
                                   placeholder={translations(`field.${USER_USERNAME}.placeholder`)}
                                   {...props[USER_USERNAME]}
                            />

                            <Input type={'password'}
                                   name={USER_PASSWORD}
                                   placeholder={translations(`field.${USER_PASSWORD}.placeholder`)}
                                   {...props[USER_PASSWORD]}
                            />

                            <Input type={'password'}
                                   name={USER_PASSWORD_CHECK}
                                   placeholder={translations(`field.${USER_PASSWORD_CHECK}.placeholder`)}
                                   {...props[USER_PASSWORD_CHECK]}
                            />

                            <SegmentInline>
                                <Button positive floated={"left"}>{translations('submit')}</Button>
                                <Button negative floated={"right"} onClick={() => onCancel && onCancel()}>
                                    {translations('cancel')}
                                </Button>
                            </SegmentInline>
                        </GridColumn>
                        <GridColumn width={6}>
                            <GridRow>
                                <EntitySelector
                                    name={USER_GROUPS}
                                    popupTitle={translations(`field.${USER_GROUPS}.title`)}
                                    title={translations(`field.${USER_GROUPS}.title`)}
                                    {...props[USER_GROUPS]}/>
                            </GridRow>
                        </GridColumn>
                    </Grid>
                </Form>
            </Formik>
        </>
    )

}