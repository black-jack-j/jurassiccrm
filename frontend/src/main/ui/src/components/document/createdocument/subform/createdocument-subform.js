import {onSubmitValueTransformer as researchValuesTransformer, ResearchMaterialSubform} from "./researchmaterial";
import {TechnologicalMapSubform} from "./technologicalmap";
import {DinosaurPassportSubFormContainer} from "./dinosaurpassport/subform-container";
import {AviaryPassportSubFormContainer} from "./aviarypassport/subform-container";

import DinosaurPassportSubFormInitialValues from "./dinosaurpassport/initialValues"
import AviaryPassportSubFormInitialValues from "./aviarypassport/initialValues"
import ResearchMaterialInitialValues from "./researchmaterial/initialValues"
import TechnologicalMapInitialValues from "./technologicalmap/initialValues"
import ThemeZoneProjectInitialValues from "./themezoneproject/initialValues"
import {ThemeZoneProjectSubFormContainer} from "./themezoneproject/subform-container";
import {CreateDocumentDocumentTypeEnum} from "../../../../generatedclient/apis";

const IDENTITY_TRANSFORMER = values => values

export const withType = (documentType) => {

    switch (documentType) {
        case CreateDocumentDocumentTypeEnum.DinosaurPassport: {
            return [DinosaurPassportSubFormContainer, IDENTITY_TRANSFORMER, DinosaurPassportSubFormInitialValues]
        }
        case CreateDocumentDocumentTypeEnum.AviaryPassport: {
            return [AviaryPassportSubFormContainer, IDENTITY_TRANSFORMER, AviaryPassportSubFormInitialValues]
        }
        case CreateDocumentDocumentTypeEnum.ResearchData: {
            return [ResearchMaterialSubform, researchValuesTransformer, ResearchMaterialInitialValues]
        }
        case CreateDocumentDocumentTypeEnum.TechnologicalMap: {
            return [TechnologicalMapSubform, IDENTITY_TRANSFORMER, TechnologicalMapInitialValues]
        }
        case CreateDocumentDocumentTypeEnum.ThemeZoneProject: {
            return [ThemeZoneProjectSubFormContainer, IDENTITY_TRANSFORMER, ThemeZoneProjectInitialValues]
        }
    }

}