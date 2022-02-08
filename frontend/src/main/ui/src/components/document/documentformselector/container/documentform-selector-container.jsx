import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../api";
import {DocumentFormSelector} from "../documentform-selector";
import {
    CreateDocumentDocumentTypeEnum,
    FindAllByRolesAllRolesEnum as UserRolesEnum
} from "../../../../generatedclient/apis";

const DOCUMENT_FORMS = [
    CreateDocumentDocumentTypeEnum.DinosaurPassport,
    CreateDocumentDocumentTypeEnum.AviaryPassport,
    CreateDocumentDocumentTypeEnum.ResearchData,
    CreateDocumentDocumentTypeEnum.TechnologicalMap,
    CreateDocumentDocumentTypeEnum.ThemeZoneProject
]

const ROLE_TO_DOCUMENT_MAPPING = {
    [UserRolesEnum.DocumentWriter]: [...DOCUMENT_FORMS],
    [UserRolesEnum.Admin]: [...DOCUMENT_FORMS],
    [UserRolesEnum.DinosaurPassportWriter]: [CreateDocumentDocumentTypeEnum.DinosaurPassport],
    [UserRolesEnum.AviaryPassportWriter]: [CreateDocumentDocumentTypeEnum.AviaryPassport],
    [UserRolesEnum.TechnologicalMapWriter]: [CreateDocumentDocumentTypeEnum.TechnologicalMap],
    [UserRolesEnum.ThemeZoneProjectWriter]: [CreateDocumentDocumentTypeEnum.ThemeZoneProject]
}

const getAvailableFormTypes = roles => Array.from(
    new Set(roles.filter(role => role in ROLE_TO_DOCUMENT_MAPPING)
        .flatMap(role => ROLE_TO_DOCUMENT_MAPPING[role]))
)

export const DocumentFormSelectorContainer = (props) => {

    const API = useContext(ApiContext)

    const [availableTypes, setAvailableTypes] = useState([])

    useEffect(() => {
        API.user.getCurrentUserRoles()
            .then(Array.from)
            .then(getAvailableFormTypes)
            .then(setAvailableTypes)
            .catch(console.error)
    }, [])

    return (
        <DocumentFormSelector values={availableTypes} {...props}/>
    )
}

