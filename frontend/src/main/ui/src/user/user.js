import React, {useContext, useEffect, useState} from "react";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../generatedclient/apis";
import ApiContext from "../api";
import {TaskTOTaskTypeEnum as TaskType, UserWithRolesTORolesEnum as Role} from "../generatedclient/models";

const ROLES_TO_EDIT_AVIARY = [Role.Admin, Role.DocumentWriter, Role.AviaryPassportWriter]
const ROLES_TO_EDIT_DINOSAUR = [Role.Admin, Role.DocumentWriter, Role.DinosaurPassportWriter]
const ROLES_TO_EDIT_RESEARCH = [Role.Admin, Role.DocumentWriter, Role.ResearchDataWriter]
const ROLES_TO_EDIT_TECH_MAP = [Role.Admin, Role.DocumentWriter, Role.TechnologicalMapWriter]
const ROLES_TO_EDIT_PROJECT = [Role.Admin, Role.DocumentWriter, Role.ThemeZoneProjectWriter]

const ROLES_TO_VIEW_AVIARY = [Role.Admin, Role.DocumentReader, Role.AviaryPassportWriter]
const ROLES_TO_VIEW_DINOSAUR = [Role.Admin, Role.DocumentReader, Role.DinosaurPassportReader]
const ROLES_TO_VIEW_RESEARCH = [Role.Admin, Role.DocumentReader, Role.ResearchDataReader]
const ROLES_TO_VIEW_TECH_MAP = [Role.Admin, Role.DocumentReader, Role.TechnologicalMapReader]
const ROLES_TO_VIEW_PROJECT = [Role.Admin, Role.DocumentReader, Role.ThemeZoneProjectReader]

const ROLES_TO_EDIT_AVIARY_TASK = [Role.Admin, Role.TaskWriter, Role.AviaryBuildingTaskWriter]
const ROLES_TO_EDIT_INCUBATION_TASK = [Role.Admin, Role.TaskWriter, Role.IncubationTaskWriter]
const ROLES_TO_EDIT_RESEARCH_TASK = [Role.Admin, Role.TaskWriter, Role.ResearchTaskWriter]

const ROLES_TO_VIEW_AVIARY_TASKS = [...ROLES_TO_EDIT_AVIARY_TASK, Role.TaskReader, Role.AviaryBuildingTaskReader]
const ROLES_TO_VIEW_INCUBATION_TASKS = [...ROLES_TO_EDIT_INCUBATION_TASK, Role.TaskReader, Role.IncubationTaskReader]
const ROLES_TO_VIEW_RESEARCH_TASKS = [...ROLES_TO_EDIT_RESEARCH_TASK, Role.TaskReader, Role.ResearchTaskReader]

const ROLES_TO_EDIT_USERS = [Role.Admin, Role.SecurityWriter]
const ROLES_TO_VIEW_USERS = [Role.Admin, Role.SecurityReader]

const editRolesByType = {
    [DocumentType.AviaryPassport]: ROLES_TO_EDIT_AVIARY,
    [DocumentType.DinosaurPassport]: ROLES_TO_EDIT_DINOSAUR,
    [DocumentType.ResearchData]: ROLES_TO_EDIT_RESEARCH,
    [DocumentType.TechnologicalMap]: ROLES_TO_EDIT_TECH_MAP,
    [DocumentType.ThemeZoneProject]: ROLES_TO_EDIT_PROJECT
}

const viewRolesByType = {
    [DocumentType.AviaryPassport]: ROLES_TO_VIEW_AVIARY,
    [DocumentType.DinosaurPassport]: ROLES_TO_VIEW_DINOSAUR,
    [DocumentType.ResearchData]: ROLES_TO_VIEW_RESEARCH,
    [DocumentType.TechnologicalMap]: ROLES_TO_VIEW_TECH_MAP,
    [DocumentType.ThemeZoneProject]: ROLES_TO_VIEW_PROJECT
}

const editTaskRolesByType = {
    [TaskType.Research]: ROLES_TO_EDIT_RESEARCH_TASK,
    [TaskType.Incubation]: ROLES_TO_EDIT_INCUBATION_TASK,
    [TaskType.AviaryCreation]: ROLES_TO_EDIT_AVIARY_TASK
}

const viewTaskRolesByType = {
    [TaskType.Research]: ROLES_TO_VIEW_RESEARCH_TASKS,
    [TaskType.Incubation]: ROLES_TO_VIEW_INCUBATION_TASKS,
    [TaskType.AviaryCreation]: ROLES_TO_VIEW_AVIARY_TASKS
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

    canEditDocuments() {
        return Object.values(DocumentType)
            .flatMap(type => editRolesByType[type])
            .some(role => this.roles.includes(role))
    }

    canEditTasks() {
        return Object.values(TaskType)
            .flatMap(type => editTaskRolesByType[type])
            .some(role => this.roles.includes(role))
    }

    canEditTaskOfType(taskType) {
        const neededRoles = editTaskRolesByType[taskType]
        return neededRoles && neededRoles.some(role => this.roles.includes(role))
    }

    canEditUsers() {
        return ROLES_TO_EDIT_USERS.some(role => this.roles.includes(role))
    }

    canEditGroups() {
        return ROLES_TO_EDIT_USERS.some(role => this.roles.includes(role))
    }

    canViewUsers() {
        return ROLES_TO_VIEW_USERS.some(role => this.roles.includes(role))
    }

    canViewGroups() {
        return ROLES_TO_VIEW_USERS.some(role => this.roles.includes(role))
    }

    canViewTasks() {
        return Object.values(TaskType)
            .flatMap(type => viewTaskRolesByType[type])
            .some(role => this.roles.includes(role))
    }
    
    canViewDocuments() {
        return Object.values(DocumentType)
            .flatMap(type => viewRolesByType[type])
            .some(role => this.roles.includes(role))
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