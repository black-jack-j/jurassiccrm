import {
    AVIARIES_SELECTOR, DECORATIONS_SELECTOR,
    DINOSAURS_SELECTOR,
    DOCUMENT_NAME,
    THEME_ZONE_PROJECT_MANAGER,
    THEME_ZONE_PROJECT_NAME
} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../../generatedclient/apis";

const BASKET_TRANSFORMER = values => values.reduce((acc, val) => {
    acc[val.item.value] = val.count
    return acc
}, {})

export default form => ({

    name: form[DOCUMENT_NAME],
    documentType: DocumentType.ThemeZoneProject,
    projectName: form[THEME_ZONE_PROJECT_NAME],
    managerId: form[THEME_ZONE_PROJECT_MANAGER].value,
    dinosaurs: BASKET_TRANSFORMER(form[DINOSAURS_SELECTOR]),
    aviaries: BASKET_TRANSFORMER(form[AVIARIES_SELECTOR]),
    decorations: BASKET_TRANSFORMER(form[DECORATIONS_SELECTOR])

})