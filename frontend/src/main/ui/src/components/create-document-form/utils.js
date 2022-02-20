import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {SuspendableResearchDataFormContainer} from "../research-data-form/suspendable-research-data-form-container";
import {SuspendableDinosaurPassportFormContainer} from "../dinosaur-passport-form/suspendable-dinosaur-passport-form-container";
import {SuspendableAviaryPassportFormContainer} from "../aviary-passport-form/suspendable-aviary-passport-form-container";
import {SuspendableThemeZoneProjectFormContainer} from "../theme-zone-project-form/suspendable-theme-zone-project-form-container";
import {SuspendableTechnologicalMapFormContainer} from "../technologicalmap-form/suspendable-technologicalmap-form-container";

import {create as createResearch} from "../research-data-form/create";
import {create as createAviary} from "../aviary-passport-form/create";
import {create as createDinosaur} from "../dinosaur-passport-form/create"
import {create as createTZP} from "../theme-zone-project-form/create"
import {create as createTM} from "../technologicalmap-form/create"

import RDInitialValues from '../research-data-form/initialValues'
import APInitialValues from '../aviary-passport-form/initialValues'
import DPInitialValues from '../dinosaur-passport-form/initialValues'
import TZPInitialValues from '../theme-zone-project-form/initialValues'
import TMInitialValues from '../technologicalmap-form/initialValues'


const formMap = {
    [DocumentTypeEnum.ResearchData]: [SuspendableResearchDataFormContainer, createResearch, RDInitialValues],
    [DocumentTypeEnum.DinosaurPassport]: [SuspendableDinosaurPassportFormContainer, createDinosaur, DPInitialValues],
    [DocumentTypeEnum.AviaryPassport]: [SuspendableAviaryPassportFormContainer, createAviary, APInitialValues],
    [DocumentTypeEnum.ThemeZoneProject]: [SuspendableThemeZoneProjectFormContainer, createTZP, TZPInitialValues],
    [DocumentTypeEnum.TechnologicalMap]: [SuspendableTechnologicalMapFormContainer, createTM, TMInitialValues]
}

export const withType = documentType => {

    return formMap[documentType]

}