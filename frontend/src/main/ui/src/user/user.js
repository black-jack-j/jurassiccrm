import React, {useContext, useEffect, useState} from "react";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../generatedclient/apis";
import ApiContext from "../api";
import {UserWithRolesTORolesEnum as Role} from "../generatedclient/models";

const ROLES_TO_EDIT_AVIARY = [Role.Admin, Role.DocumentWriter, Role.AviaryPassportWriter]
const ROLES_TO_EDIT_DINOSAUR = [Role.Admin, Role.DocumentWriter, Role.DinosaurPassportWriter]
const ROLES_TO_EDIT_RESEARCH = [Role.Admin, Role.DocumentWriter, Role.ResearchDataWriter]
const ROLES_TO_EDIT_TECH_MAP = [Role.Admin, Role.DocumentWriter, Role.TechnologicalMapWriter]
const ROLES_TO_EDIT_PROJECT = [Role.Admin, Role.DocumentWriter, Role.ThemeZoneProjectWriter]

const editRolesByType = {
    [DocumentType.AviaryPassport]: ROLES_TO_EDIT_AVIARY,
    [DocumentType.DinosaurPassport]: ROLES_TO_EDIT_DINOSAUR,
    [DocumentType.ResearchData]: ROLES_TO_EDIT_RESEARCH,
    [DocumentType.TechnologicalMap]: ROLES_TO_EDIT_TECH_MAP,
    [DocumentType.ThemeZoneProject]: ROLES_TO_EDIT_PROJECT
}

export class User {

    constructor(userTO) {
        this.id = userTO.id
        this.roles = userTO.roles
        this.groups = userTO.groups
        this.username = userTO.username
        this.firstName = userTO.firstName
        this.lastName = userTO.lastName
        this.department = userTO.department
    }

    canEditDocumentOfType(documentType) {
        const neededRoles = editRolesByType[documentType]
        return neededRoles && neededRoles.some(role => this.roles.includes(role))
    }

}

export const useUsersSimple = () => {

    const [result, setResult] = useState({state: 'loading', users: [], error: null})

    const API = useContext(ApiContext)

    const refresh = () => API.user.getUsersSimple()
        .then(users => setResult({state: 'loaded', users, error: null}))
        .catch(error => setResult({state: 'error', users: [], error}))

    useEffect(() => {
        refresh()
    }, [])

    return [result, refresh]
}

export const useUser = userId => {

    const [result, setResult] = useState({state: 'loading', user: null, error: null})

    const API = useContext(ApiContext)

    const reload = () => {
        API.user.getUserById({userId}).then(user => {
            setResult({state: 'loaded', user, error: null})
        }).catch(error => {
            setResult({state: 'error', user: null, error})
        })
    }

    useEffect(() => {
        reload()
        return () => setResult({state: 'loading', user: null, error: null})
    }, [userId])

    return [result, reload]

}