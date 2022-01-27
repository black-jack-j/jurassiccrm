import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../../api";
import {
    AVIARY_PASSPORT,
    DINOSAUR_PASSPORT,
    RESEARCH_MATERIAL,
    TECHNOLOGICAL_MAP,
    THEME_ZONE_PROJECT
} from "../../createdocument/subform/createdocument-subform";
import {DocumentFormSelector} from "../documentform-selector";
import {UserRolesEnum} from "../../../../generatedclient/models";

const DOCUMENT_FORMS = [
    DINOSAUR_PASSPORT,
    AVIARY_PASSPORT,
    RESEARCH_MATERIAL,
    TECHNOLOGICAL_MAP,
    THEME_ZONE_PROJECT
]

const ROLE_TO_DOCUMENT_MAPPING = {
    [UserRolesEnum.DocumentWriter]: [...DOCUMENT_FORMS],
    [UserRolesEnum.Admin]: [...DOCUMENT_FORMS],
    [UserRolesEnum.DinosaurPassportWriter]: [DINOSAUR_PASSPORT],
    [UserRolesEnum.AviaryPassportWriter]: [AVIARY_PASSPORT],
    [UserRolesEnum.TechnologicalMapWriter]: [TECHNOLOGICAL_MAP],
    [UserRolesEnum.ThemeZoneProjectWriter]: [THEME_ZONE_PROJECT]
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

