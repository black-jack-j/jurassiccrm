import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient"
import {AVIARY_BUILT_DATE} from "./fieldNames";
import {stringDateToInstant} from "../../time/time-utils";

export const create = API => values => {

    const TO = {...values}
    TO[AVIARY_BUILT_DATE] = stringDateToInstant(TO[AVIARY_BUILT_DATE]).toEpochMilli()

    API.document.createDocument({documentType: DocumentTypeEnum.AviaryPassport, body: TO})
}