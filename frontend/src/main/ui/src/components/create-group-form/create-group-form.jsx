import './create-group-form.css'
import {Container, Grid, GridColumn, Menu, MenuItem} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {useContext, useEffect, useState} from "react";
import {Form, Input, ResetButton, Select, SubmitButton} from "formik-semantic-ui-react";
import {Formik} from "formik";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "./fieldNames";
import {EntitySelector} from "../entity-selector/entity-selector";
import ApiContext from "../../api";
import {GroupInputTOFromJSON} from "../../generatedclient/models";

const userToOption = ({id, firstName, lastName}) => ({id, value: id, text: `${firstName} ${lastName}`})

const privilegeToOption = t => privilege => ({id: privilege, value: privilege, text: t(`crm.privilege.${privilege}`)})

export const CreateGroupForm = props => {

    const {
        onSubmit,
        onCancel,
        formik
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.group.form.create'})

    return (
        <>
            <Container className={'create-group-form'}>
                <Menu className={'create-group-form__header'} secondary>
                    <MenuItem header>
                        {t('title')}
                    </MenuItem>
                </Menu>
                <Formik enableReinitialize onSubmit={onSubmit} onReset={onCancel} initialValues={formik.initialValues}>
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
                               <EntitySelector name={GROUP_MEMBERS}
                                               title={t(`field.${GROUP_MEMBERS}.title`)}
                                               {...props[GROUP_MEMBERS]}/>
                           </GridColumn>
                           <GridColumn>
                               <EntitySelector name={GROUP_PRIVILEGES}
                                               title={t(`field.${GROUP_PRIVILEGES}.title`)}
                                               {...props[GROUP_PRIVILEGES]}/>
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

export const CreateGroupFormContainer = props => {

    const {t} = useTranslation()

    const API = useContext(ApiContext)

    const [groupMemberOptions, setGroupMemberOptions] = useState([])
    const [privilegesOptions, setPrivilegesOptions] = useState([])

    useEffect(() => {
        API.user.getUsers().then(users => {
            return users.map(userToOption)
        }).then(setGroupMemberOptions)
            .catch(console.error)

        API.role.getAllRoles().then(roles => {
            return roles.map(privilegeToOption(t))
        }).then(setPrivilegesOptions)
            .catch(console.error)
    }, [])

    const onSubmit = values => {

        props.onSubmit && props.onSubmit(values)

        const TO = {
            [GROUP_NAME]: values[GROUP_NAME],
            [GROUP_DESCRIPTION]: values[GROUP_DESCRIPTION],
            [GROUP_MEMBERS]: values[GROUP_MEMBERS].map(entity => entity.value),
            [GROUP_PRIVILEGES]: values[GROUP_PRIVILEGES].map(entity => entity.value),
            [GROUP_ICON]: values[GROUP_ICON],
        }

        API.group.createGroup({body: GroupInputTOFromJSON(TO)})
            .then(console.log)
            .catch(console.error)
    }

    const innerProps = {
        ...props,
        [GROUP_MEMBERS]: {
            ...props[GROUP_MEMBERS],
            options: groupMemberOptions
        },
        [GROUP_PRIVILEGES]: {
            ...props[GROUP_PRIVILEGES],
            options: privilegesOptions
        },
        onSubmit
    }

    return <CreateGroupForm {...innerProps}/>

}