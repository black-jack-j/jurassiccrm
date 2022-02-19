import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import serialize from "./serialize";

export const create = API => values => {

    const TO = serialize(values)

    API.document.createDocument({documentType: DocumentTypeEnum.DinosaurPassport, body: TO})
}