import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {THEME_ZONE_FORMIK_TRANSFORMER} from "./theme-zone-project-form-container";

export default API => documentId => values => {

   return API.document.updateDocument({
       documentId,
       documentType: DocumentTypeEnum.ThemeZoneProject,
       body: THEME_ZONE_FORMIK_TRANSFORMER(values)
   })
}