import {
    DINOSAUR_DESCRIPTION,
    DINOSAUR_HEIGHT, DINOSAUR_INCUBATION_DATE,
    DINOSAUR_NAME, DINOSAUR_REVISION_PERIOD, DINOSAUR_STATUS,
    DINOSAUR_TYPE_ID,
    DINOSAUR_WEIGHT,
    DOCUMENT_NAME
} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../../generatedclient/apis";
import {stringDateToInstant} from "../../time/time-utils";


export default form => ({
    name: form[DOCUMENT_NAME],
    description: form[DINOSAUR_DESCRIPTION],
    documentType: DocumentType.DinosaurPassport,
    dinosaurTypeId: form[DINOSAUR_TYPE_ID].value,
    dinosaurName: form[DINOSAUR_NAME],
    weight: form[DINOSAUR_WEIGHT],
    height: form[DINOSAUR_HEIGHT],
    incubated: stringDateToInstant(form[DINOSAUR_INCUBATION_DATE]).toEpochMilli(),
    revisionPeriod: form[DINOSAUR_REVISION_PERIOD],
    status: form[DINOSAUR_STATUS]
})