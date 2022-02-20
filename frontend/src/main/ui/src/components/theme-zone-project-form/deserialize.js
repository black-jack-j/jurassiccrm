import {
    AVIARIES_SELECTOR,
    DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR, DOCUMENT_NAME,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";

const toFormikBasket = array => array.map(({number, type}) => ({
    item: {value: type.id, text: type.name}, count: number
}))

export default TO => ({

    [THEME_ZONE_PROJECT_NAME]: TO[THEME_ZONE_PROJECT_NAME],
    [THEME_ZONE_PROJECT_MANAGER]: {
        key: TO.manager ? TO.manager.id : '',
        value: TO.manager ? TO.manager.id : '',
        text: TO.manager ? `${TO.manager.firstName} ${TO.manager.lastName}` : ''
    },
    [DINOSAURS_SELECTOR]: toFormikBasket(TO.dinosaurs),
    [AVIARIES_SELECTOR]: toFormikBasket(TO.aviaries),
    [DECORATIONS_SELECTOR]: toFormikBasket(TO.decorations),
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME]

})