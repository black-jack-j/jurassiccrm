import {
    AVIARY_BUILT_DATE,
    AVIARY_CODE,
    AVIARY_DESCRIPTION,
    AVIARY_REVISION_PERIOD,
    AVIARY_SQUARE,
    AVIARY_STATUS,
    AVIARY_TYPE_ID,
    DOCUMENT_NAME
} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../../generatedclient/apis";
import {stringDateToInstant} from "../../time/time-utils";


export default form => ({
    name: form[DOCUMENT_NAME],
    description: form[AVIARY_DESCRIPTION],
    documentType: DocumentType.AviaryPassport,
    aviaryTypeId: form[AVIARY_TYPE_ID].value,
    code: form[AVIARY_CODE],
    square: form[AVIARY_SQUARE],
    builtDate: stringDateToInstant(form[AVIARY_BUILT_DATE]).toEpochMilli(),
    revisionPeriod: form[AVIARY_REVISION_PERIOD],
    status: form[AVIARY_STATUS]
})