import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";

export default API => documentId => values => API.document.updateDocument({
    documentId,
    documentType: DocumentTypeEnum.TechnologicalMap,
    body: values
})