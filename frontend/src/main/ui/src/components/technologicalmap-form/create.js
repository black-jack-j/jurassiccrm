import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";

export const create = API => values => API.document.createDocument({documentType: DocumentTypeEnum.TechnologicalMap, body: values})