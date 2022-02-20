import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import serialize from "./serialize";

export const create = API => values => {

    const TO = serialize(values)

    return API.document.createDocument({
        documentType: DocumentTypeEnum.ThemeZoneProject,
        body: TO
    })
}