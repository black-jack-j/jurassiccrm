import {ResearchMaterialSubform} from "./researchmaterial";
import {TechnologicalMapSubform} from "./technologicalmap";
import {ThemeZoneProjectSubform} from "./themezoneproject";
import {DinosaurPassportSubFromContainer} from "./dinosaurpassport/subform-container";
import {AviaryPassportSubFormContainer} from "./aviarypassport/subform-container";

export const DINOSAUR_PASSPORT = 'dinosaur_passport'
export const AVIARY_PASSPORT = 'aviary_passport'
export const RESEARCH_MATERIAL = 'research_material'
export const TECHNOLOGICAL_MAP = 'technological_map'
export const THEME_ZONE_PROJECT = 'theme_zone_project'

export const withType = (documentType) => {

    switch (documentType) {
        case DINOSAUR_PASSPORT: {
            return [DinosaurPassportSubFromContainer]
        }
        case AVIARY_PASSPORT: {
            return [AviaryPassportSubFormContainer]
        }
        case RESEARCH_MATERIAL: {
            return [ResearchMaterialSubform]
        }
        case TECHNOLOGICAL_MAP: {
            return [TechnologicalMapSubform]
        }
        case THEME_ZONE_PROJECT: {
            return [ThemeZoneProjectSubform]
        }
    }

}