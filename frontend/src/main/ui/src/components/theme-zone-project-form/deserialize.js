import {
    AVIARIES_SELECTOR,
    DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR, DOCUMENT_NAME,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";

export default TO => ({

    [THEME_ZONE_PROJECT_NAME]: TO[THEME_ZONE_PROJECT_NAME],
    [THEME_ZONE_PROJECT_MANAGER]: {
        key: TO.manager.id,
        value: TO.manager.id,
        text: `${TO.manager.firstName} ${TO.manager.lastName}`
    },
    [DINOSAURS_SELECTOR]: [...TO.dinosaurs],
    [AVIARIES_SELECTOR]: [...TO.aviaries],
    [DECORATIONS_SELECTOR]: [...TO.decorations],
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME]

})