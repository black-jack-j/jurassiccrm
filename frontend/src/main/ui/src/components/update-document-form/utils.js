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

import RDInitialValues from '../research-data-form/initialValues'
import APInitialValues from '../aviary-passport-form/initialValues'
import DPInitialValues from '../dinosaur-passport-form/initialValues'
import TZPInitialValues from '../theme-zone-project-form/initialValues'
import TMInitialValues from '../technologicalmap-form/initialValues'


const formMap = {
    [DocumentTypeEnum.ResearchData]: [SuspendableResearchDataFormContainer, updateResearch, RDInitialValues],
    [DocumentTypeEnum.DinosaurPassport]: [SuspendableDinosaurPassportFormContainer, updateDinosaur, DPInitialValues],
    [DocumentTypeEnum.AviaryPassport]: [SuspendableAviaryPassportFormContainer, updateAviary, APInitialValues],
    [DocumentTypeEnum.ThemeZoneProject]: [SuspendableThemeZoneProjectFormContainer, updateTZP, TZPInitialValues],
    [DocumentTypeEnum.TechnologicalMap]: [SuspendableTechnologicalMapFormContainer, updateTM, TMInitialValues]
}

export const withType = documentType => {

    return formMap[documentType]

}