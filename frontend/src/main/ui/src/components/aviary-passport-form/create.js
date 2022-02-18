import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient"
import serialize from "./serialize";

export const create = API => values => {

    const TO = serialize(values)

    API.document.createDocument({documentType: DocumentTypeEnum.AviaryPassport, body: TO})
}