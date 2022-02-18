import {AVIARY_BUILT_DATE} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {stringDateToInstant} from "../../time/time-utils";

export default API => documentId => values => {

    const TO = {...values}
    TO[AVIARY_BUILT_DATE] = stringDateToInstant(TO[AVIARY_BUILT_DATE]).toEpochMilli()

    return API.document.updateDocument({
        documentId,
        documentType: DocumentTypeEnum.AviaryPassport,
        body: TO
    })

}