import './group-form.css'
import {Container, Grid, GridColumn, Menu, MenuItem, Segment} from "semantic-ui-react";
import React from "react";
import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import {Formik} from "formik";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "./fieldNames";
import {EntitySelector} from "../entity-selector/entity-selector";
import {UserIcon} from "../jurassic_icon/user/user-icon";
import {FormikAvatarSelector, FormikAvatarSelectorPreview} from "../avatar-selector/formik-avatar-selector";


export const GroupForm = props => {

    const {
        onSubmit,
        onCancel,
        initialValues,
        translations = key => key
    } = props

    return (
        <>
            <Container className={'group-form'}>
                <Menu className={'group-form__header'} secondary>
                    <MenuItem header>
                        {translations('title')}
                    </MenuItem>
                </Menu>
                <Formik onSubmit={onSubmit} onReset={onCancel} initialValues={initialValues}>
                    <Form>
                        <Input name={GROUP_NAME}
                               placeholder={translations(`field.${GROUP_NAME}.placeholder`)}
                               {...props[GROUP_NAME]}
                        />
                        <Segment>
                            <Grid columns={2}>
                                <GridColumn width={2}>
                                    <FormikAvatarSelectorPreview Placeholder={() => <UserIcon circular size={"large"}/>} name={GROUP_ICON}/>
                                </GridColumn>
                                <GridColumn>
                                    <FormikAvatarSelector name={GROUP_ICON}/>
                                </GridColumn>
                            </Grid>
                        </Segment>
                        <Input name={GROUP_DESCRIPTION}
                               placeholder={translations(`field.${GROUP_DESCRIPTION}.placeholder`)}
                               {...props[GROUP_DESCRIPTION]}/>
                       <Grid columns={2}>
                           <GridColumn>
                               <EntitySelector name={GROUP_MEMBERS}
                                               title={translations(`field.${GROUP_MEMBERS}.title`)}
                                               {...props[GROUP_MEMBERS]}/>
                           </GridColumn>
                           <GridColumn>
                               <EntitySelector name={GROUP_PRIVILEGES}
                                               title={translations(`field.${GROUP_PRIVILEGES}.title`)}
                                               {...props[GROUP_PRIVILEGES]}/>
                           </GridColumn>
                       </Grid>
                        <div className={'group-form__controls'}>
                            <SubmitButton className={'group-form__submit'} positive content={translations('submit')}/>
                            <ResetButton className={'group-form__cancel'} negative content={translations('cancel')}/>
                        </div>
                    </Form>
                </Formik>
            </Container>
        </>
    )

}