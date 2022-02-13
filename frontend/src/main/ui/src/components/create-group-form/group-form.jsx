import './group-form.css'
import {Container, Grid, GridColumn, Menu, MenuItem, Segment} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {useContext, useEffect, useState} from "react";
import {Form, Input, ResetButton, SubmitButton} from "formik-semantic-ui-react";
import {Formik} from "formik";
import {GROUP_DESCRIPTION, GROUP_ICON, GROUP_MEMBERS, GROUP_NAME, GROUP_PRIVILEGES} from "./fieldNames";
import {EntitySelector} from "../entity-selector/entity-selector";
import ApiContext from "../../api";
import {GroupInputTOFromJSON} from "../../generatedclient/models";
import {UserIcon} from "../jurassic_icon/user/user-icon";
import {FormikAvatarSelector, FormikAvatarSelectorPreview} from "../formik-avatar-selector";

const userToOption = ({id, firstName, lastName}) => ({id, value: id, text: `${firstName} ${lastName}`})

const privilegeToOption = t => privilege => ({id: privilege, value: privilege, text: t(`crm.privilege.${privilege}`)})

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
                <Formik enableReinitialize onSubmit={onSubmit} onReset={onCancel} initialValues={initialValues}>
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

export const CreateGroupFormContainer = props => {

    const {t} = useTranslation()

    const translations = key => t(`crm.group.form.create.${key}`)

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

    return <GroupForm translations={translations} {...innerProps}/>

}