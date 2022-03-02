import {
    CreateDocumentDocumentTypeEnum as DocumentType,
    CreateDocumentDocumentTypeEnum as DocumentTypeEnum
} from "../../generatedclient/apis";
import {
    AVIARIES_SELECTOR, DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR,
    DOCUMENT_NAME,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";

const BASKET_TRANSFORMER = values => values.reduce((acc, val) => {
    acc[val.item] = val.count
    return acc
}, {})

const serialize = form => ({

    name: form[DOCUMENT_NAME],
    documentType: DocumentType.ThemeZoneProject,
    projectName: form[THEME_ZONE_PROJECT_NAME],
    managerId: form[THEME_ZONE_PROJECT_MANAGER],
    dinosaurs: BASKET_TRANSFORMER(form[DINOSAURS_SELECTOR]),
    aviaries: BASKET_TRANSFORMER(form[AVIARIES_SELECTOR]),
    decorations: BASKET_TRANSFORMER(form[DECORATIONS_SELECTOR])

})

export const create = API => values => {

    const TO = serialize(values)

    return API.document.createDocument({
        documentType: DocumentTypeEnum.ThemeZoneProject,
        body: TO
    }, {body: JSON.stringify(TO)})
}