import {TechnologicalMapSubform} from "./technologicalmap";
import {DinosaurPassportSubFormContainer} from "./dinosaurpassport/subform-container";
import {AviaryPassportSubFormContainer} from "./aviarypassport/subform-container";

import DinosaurPassportSubFormInitialValues from "./dinosaurpassport/initialValues"
import AviaryPassportSubFormInitialValues from "./aviarypassport/initialValues"
import TechnologicalMapInitialValues from "./technologicalmap/initialValues"
import ThemeZoneProjectInitialValues from "./themezoneproject/initialValues"
import {ThemeZoneProjectSubFormContainer} from "./themezoneproject/subform-container";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../generatedclient/apis";

const STRING_BODY_TRANSFORMER = values => JSON.stringify(values)

export const withType = (documentType) => {

    switch (documentType) {
        case DocumentTypeEnum.DinosaurPassport: {
            return [DinosaurPassportSubFormContainer, STRING_BODY_TRANSFORMER, DinosaurPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.AviaryPassport: {
            return [AviaryPassportSubFormContainer, STRING_BODY_TRANSFORMER, AviaryPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.TechnologicalMap: {
            return [TechnologicalMapSubform, STRING_BODY_TRANSFORMER, TechnologicalMapInitialValues]
        }
        case DocumentTypeEnum.ThemeZoneProject: {
            return [ThemeZoneProjectSubFormContainer, STRING_BODY_TRANSFORMER, ThemeZoneProjectInitialValues]
        }
    }

}