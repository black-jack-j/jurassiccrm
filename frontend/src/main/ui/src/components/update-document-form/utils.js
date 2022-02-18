import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {SuspendableResearchDataFormContainer} from "../research-data-form/suspendable-research-data-form-container";
import {SuspendableDinosaurPassportFormContainer} from "../dinosaur-passport-form/suspendable-dinosaur-passport-form-container";
import {SuspendableAviaryPassportFormContainer} from "../aviary-passport-form/suspendable-aviary-passport-form-container";
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


const formMap = {
    [DocumentTypeEnum.ResearchData]: [SuspendableResearchDataFormContainer, updateResearch, deserializeResearch],
    [DocumentTypeEnum.DinosaurPassport]: [SuspendableDinosaurPassportFormContainer, updateDinosaur, deserializeDP],
    [DocumentTypeEnum.AviaryPassport]: [SuspendableAviaryPassportFormContainer, updateAviary, deserializeAP],
    [DocumentTypeEnum.ThemeZoneProject]: [SuspendableThemeZoneProjectFormContainer, updateTZP, deserializeTZP],
    [DocumentTypeEnum.TechnologicalMap]: [SuspendableTechnologicalMapFormContainer, updateTM, deserializeTM]
}

export const withType = documentType => {

    return formMap[documentType]

}