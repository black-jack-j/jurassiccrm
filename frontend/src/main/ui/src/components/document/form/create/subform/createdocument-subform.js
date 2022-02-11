import {DinosaurPassportSubFormContainer} from "./dinosaurpassport/subform-container";
import {AviaryPassportSubFormContainer} from "./aviarypassport/subform-container";

import DinosaurPassportSubFormInitialValues from "./dinosaurpassport/initialValues"
import AviaryPassportSubFormInitialValues from "./aviarypassport/initialValues"
import TechnologicalMapInitialValues from "./technologicalmap/initialValues"
import ThemeZoneProjectInitialValues from "./themezoneproject/initialValues"
import {THEME_ZONE_FORMIK_TRANSFORMER, ThemeZoneProjectSubFormContainer} from "./themezoneproject/subform-container";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../../../../generatedclient/apis";
import {TechnologicalMapSubFormContainer} from "./technologicalmap/subform-container";

const IDENTITY_TRANSFORMER = v => v

const then = f => g => x => f(g(x))

export const withType = (documentType) => {

    switch (documentType) {
        case DocumentTypeEnum.DinosaurPassport: {
            return [DinosaurPassportSubFormContainer, IDENTITY_TRANSFORMER, DinosaurPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.AviaryPassport: {
            return [AviaryPassportSubFormContainer, IDENTITY_TRANSFORMER, AviaryPassportSubFormInitialValues]
        }
        case DocumentTypeEnum.TechnologicalMap: {
            return [TechnologicalMapSubFormContainer, IDENTITY_TRANSFORMER, TechnologicalMapInitialValues]
        }
        case DocumentTypeEnum.ThemeZoneProject: {
            return [ThemeZoneProjectSubFormContainer, then(IDENTITY_TRANSFORMER)(THEME_ZONE_FORMIK_TRANSFORMER), ThemeZoneProjectInitialValues]
        }
    }

}