import {DINOSAUR_INCUBATION_DATE} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {stringDateToInstant} from "../../time/time-utils";

export default API => documentId => values => {

    const TO = {...values}
    TO[DINOSAUR_INCUBATION_DATE] = stringDateToInstant(TO[DINOSAUR_INCUBATION_DATE]).toEpochMilli()

    return API.document.updateDocument({
        documentId,
        documentType: DocumentTypeEnum.DinosaurPassport,
        body: TO
    })

}