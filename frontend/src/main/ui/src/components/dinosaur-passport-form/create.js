import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {DINOSAUR_INCUBATION_DATE} from "./fieldNames";
import {stringDateToInstant} from "../../time/time-utils";

export const create = API => values => {

    const TO = {...values}
    TO[DINOSAUR_INCUBATION_DATE] = stringDateToInstant(TO[DINOSAUR_INCUBATION_DATE]).toEpochMilli()

    API.document.createDocument({documentType: DocumentTypeEnum.DinosaurPassport, body: TO})
}