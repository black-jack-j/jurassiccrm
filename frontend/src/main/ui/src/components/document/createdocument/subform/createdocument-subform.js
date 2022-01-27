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

export const DINOSAUR_PASSPORT = 'dinosaur_passport'
export const AVIARY_PASSPORT = 'aviary_passport'
export const RESEARCH_MATERIAL = 'research_material'
export const TECHNOLOGICAL_MAP = 'technological_map'
export const THEME_ZONE_PROJECT = 'theme_zone_project'

const IDENTITY_TRANSFORMER = values => values

export const withType = (documentType) => {

    switch (documentType) {
        case DINOSAUR_PASSPORT: {
            return [DinosaurPassportSubFormContainer, IDENTITY_TRANSFORMER, DinosaurPassportSubFormInitialValues]
        }
        case AVIARY_PASSPORT: {
            return [AviaryPassportSubFormContainer, IDENTITY_TRANSFORMER, AviaryPassportSubFormInitialValues]
        }
        case RESEARCH_MATERIAL: {
            return [ResearchMaterialSubform, researchValuesTransformer, ResearchMaterialInitialValues]
        }
        case TECHNOLOGICAL_MAP: {
            return [TechnologicalMapSubform, IDENTITY_TRANSFORMER, TechnologicalMapInitialValues]
        }
        case THEME_ZONE_PROJECT: {
            return [ThemeZoneProjectSubFormContainer, IDENTITY_TRANSFORMER, ThemeZoneProjectInitialValues]
        }
    }

}