import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {THEME_ZONE_FORMIK_TRANSFORMER} from "./theme-zone-project-form-container";

export const create = API => values => API.document.createDocument({
    documentType: DocumentTypeEnum.ThemeZoneProject,
    body: THEME_ZONE_FORMIK_TRANSFORMER(values)
})