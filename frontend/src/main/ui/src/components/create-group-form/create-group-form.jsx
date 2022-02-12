import './create-group-form.css'
import {Container, Grid, GridColumn, Menu, MenuItem} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React from "react";
import {Form, Input, ResetButton, Select, SubmitButton} from "formik-semantic-ui-react";
import {FieldArray, Formik} from "formik";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "./fieldNames";
import {Container as EntityContainer} from "../container/container"

const TextInputSteps = ({fieldName, ...props}) => {
    return <Input name={fieldName} {...props}/>
}

const GroupMembersContainer = EntityContainer(TextInputSteps)
const GroupPrivilegesContainer = EntityContainer(TextInputSteps)

export const CreateGroupForm = props => {

    const {
        onSubmit,
        formik
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.group.form.create'})

    const GroupMembersInput = GroupMembersContainer(
        GROUP_MEMBERS,
        t(`field.${GROUP_MEMBERS}.title`),
        props[GROUP_MEMBERS]
    )

    const GroupPrivilegesInput = GroupPrivilegesContainer(
        GROUP_PRIVILEGES,
        t(`field.${GROUP_PRIVILEGES}.title`),
        props[GROUP_PRIVILEGES]
    )

    return (
        <>
            <Container>
                <Menu className={'create-task-form__header'} secondary>
                    <MenuItem header>
                        {t('title')}
                    </MenuItem>
                </Menu>
                <Formik enableReinitialize onSubmit={onSubmit} initialValues={formik.initialValues}>
                    <Form>
                        <Input name={GROUP_NAME}
                               placeholder={t(`field.${GROUP_NAME}.placeholder`)}
                               {...props[GROUP_NAME]}
                        />
                        <Select name={GROUP_ICON}
                                placeholder={t(`field.${GROUP_ICON}.placeholder`)}
                                {...props[GROUP_ICON]}/>

                        <Input name={GROUP_DESCRIPTION}
                               placeholder={t(`field.${GROUP_DESCRIPTION}.placeholder`)}
                               {...props[GROUP_DESCRIPTION]}/>
                       <Grid columns={2}>
                           <GridColumn>
                               <FieldArray name={GROUP_MEMBERS} component={GroupMembersInput}/>
                           </GridColumn>
                           <GridColumn>
                               <FieldArray name={GROUP_PRIVILEGES} component={GroupPrivilegesInput}/>
                           </GridColumn>
                       </Grid>
                        <div className={'create-group-form__controls'}>
                            <SubmitButton className={'create-group-form__submit'} positive content={t('submit')}/>
                            <ResetButton className={'create-group-form__cancel'} negative content={t('cancel')}/>
                        </div>
                    </Form>
                </Formik>
            </Container>
        </>
    )

}