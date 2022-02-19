import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {SuspendableThemeZoneProjectFormContainer} from "../theme-zone-project-form/suspendable-theme-zone-project-form-container";
import {SuspendableTechnologicalMapFormContainer} from "../technologicalmap-form/suspendable-technologicalmap-form-container";

import updateResearch from "../research-data-form/update";
import updateAviary from "../aviary-passport-form/update";
import updateDinosaur from "../dinosaur-passport-form/update"
import updateTZP from "../theme-zone-project-form/update"
import updateTM from "../technologicalmap-form/update"

import deserializeResearch from "../research-data-form/deserialize";
import deserializeAP from "../aviary-passport-form/deserialize";
import deserializeTM from "../technologicalmap-form/deserialize";
import deserializeDP from "../dinosaur-passport-form/deserialize";
import deserializeTZP from "../theme-zone-project-form/deserialize";

import {SuspendableUpdateResearchDataFormContainer} from "../update-research-data-form/suspendable-update-research-data-form-container";
import {SuspendableUpdateAviaryPassportFormContainer} from "../aviary-passport-form/suspendable-update-aviary-passport-form-container";
import {SuspendableUpdateDinosaurPassportFormContainer} from "../dinosaur-passport-form/suspendable-update-dinosaur-passport-form-container";


const formMap = {
    [DocumentTypeEnum.ResearchData]: [SuspendableUpdateResearchDataFormContainer, updateResearch, deserializeResearch],
    [DocumentTypeEnum.DinosaurPassport]: [SuspendableUpdateDinosaurPassportFormContainer, updateDinosaur, deserializeDP],
    [DocumentTypeEnum.AviaryPassport]: [SuspendableUpdateAviaryPassportFormContainer, updateAviary, deserializeAP],
    [DocumentTypeEnum.ThemeZoneProject]: [SuspendableThemeZoneProjectFormContainer, updateTZP, deserializeTZP],
    [DocumentTypeEnum.TechnologicalMap]: [SuspendableTechnologicalMapFormContainer, updateTM, deserializeTM]
}

export const withType = documentType => {

    return formMap[documentType]

}