import {DinosaurPassportSubFormContainer} from "./dinosaurpassport/subform-container";
import {AviaryPassportSubFormContainer} from "./aviarypassport/subform-container";

import DinosaurPassportSubFormInitialValues from "./dinosaurpassport/initialValues"
import AviaryPassportSubFormInitialValues from "./aviarypassport/initialValues"
import TechnologicalMapInitialValues from "./technologicalmap/initialValues"
import ThemeZoneProjectInitialValues from "./themezoneproject/initialValues"
import {THEME_ZONE_FORMIK_TRANSFORMER, ThemeZoneProjectSubFormContainer} from "./themezoneproject/subform-container";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../generatedclient/apis";
import {TechnologicalMapSubFormContainer} from "./technologicalmap/subform-container";

const STRING_BODY_TRANSFORMER = values => JSON.stringify(values)

const STRING_BODY_TRANSFORMER_HO = f => v => JSON.stringify(f(v))

export const withType = (documentType) => {

    switch (documentType) {
        case DocumentTypeEnum.DinosaurPassport: {
            return [DinosaurPassportSubFormContainer, STRING_BODY_TRANSFORMER, DinosaurPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.AviaryPassport: {
            return [AviaryPassportSubFormContainer, STRING_BODY_TRANSFORMER, AviaryPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.TechnologicalMap: {
            return [TechnologicalMapSubFormContainer, STRING_BODY_TRANSFORMER, TechnologicalMapInitialValues]
        }
        case DocumentTypeEnum.ThemeZoneProject: {
            return [ThemeZoneProjectSubFormContainer, STRING_BODY_TRANSFORMER_HO(THEME_ZONE_FORMIK_TRANSFORMER), ThemeZoneProjectInitialValues]
        }
    }

}