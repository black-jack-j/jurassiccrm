import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import serialize from "./serialize";

export default API => documentId => values => {

    const TO = serialize(values)

    return API.document.updateDocument({
        documentId,
        documentType: DocumentTypeEnum.AviaryPassport,
        body: TO
    }, {body: JSON.stringify(TO)})

}