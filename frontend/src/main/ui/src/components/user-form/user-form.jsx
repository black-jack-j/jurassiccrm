import {Formik} from "formik";
import {Form, Input, Select} from "formik-semantic-ui-react";
import React from "react";
import {Button, Grid, GridColumn, GridRow, Header, Segment} from "semantic-ui-react";
import {
    USER_DEPARTMENT,
    USER_FIRSTNAME,
    USER_GROUPS,
    USER_ICON,
    USER_LASTNAME,
    USER_PASSWORD,
    USER_PASSWORD_CHECK,
    USER_USERNAME
} from "./fieldNames";
import {EntitySelector} from "../entity-selector/entity-selector";
import {FormikAvatarSelector, FormikAvatarSelectorPreview} from "../avatar-selector/formik-avatar-selector";
import {Avatar} from "../avatar/avatar";

const AvatarPlaceholder = () => <Avatar src={'/img/avatar.png'} size={"large"}/>

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
                onSubmit(values)
                resetForm()
            }} onReset={onCancel}>
                <Form>
                    <Grid columns={2}>
                        <GridColumn width={10} stretched>
                            <Input name={USER_FIRSTNAME}
                                   placeholder={translations(`field.${USER_FIRSTNAME}.placeholder`)}/>

                            <Input name={USER_LASTNAME}
                                   placeholder={translations(`field.${USER_LASTNAME}.placeholder`)}/>
                            <Select name={USER_DEPARTMENT}
                                    placeholder={translations(`field.${USER_DEPARTMENT}.placeholder`)}
                                    {...props[USER_DEPARTMENT]}/>

                            <Input name={USER_USERNAME}
                                   placeholder={translations(`field.${USER_USERNAME}.placeholder`)}/>

                            <Input type={'password'}
                                   name={USER_PASSWORD}
                                   placeholder={translations(`field.${USER_PASSWORD}.placeholder`)}/>

                            <Input type={'password'}
                                   name={USER_PASSWORD_CHECK}
                                   placeholder={translations(`field.${USER_PASSWORD_CHECK}.placeholder`)}/>

                            <Grid columns={3}>
                                <GridColumn width={5}>
                                    <Button fluid positive floated={"left"}>{translations('submit')}</Button>
                                </GridColumn>
                                <GridColumn width={6}/>
                                <GridColumn width={5}>
                                    <Button fluid negative floated={"right"} onClick={onCancel}>{translations('cancel')}</Button>
                                </GridColumn>
                            </Grid>
                        </GridColumn>
                        <GridColumn width={6}>
                            <GridRow>
                                <Segment>
                                    <Grid columns={2}>
                                        <GridColumn width={4}>
                                            <FormikAvatarSelectorPreview
                                                name={USER_ICON}
                                                Placeholder={() => <AvatarPlaceholder/>}
                                            />
                                        </GridColumn>
                                        <GridColumn verticalAlign>
                                            <FormikAvatarSelector name={USER_ICON}/>
                                        </GridColumn>
                                    </Grid>
                                </Segment>
                            </GridRow>
                            <GridRow>
                                <EntitySelector
                                    name={USER_GROUPS}
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