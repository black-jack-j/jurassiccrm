import {
    AVIARY_BUILT_DATE, AVIARY_CODE,
    AVIARY_DESCRIPTION,
    AVIARY_REVISION_PERIOD, AVIARY_SQUARE,
    AVIARY_STATUS,
    AVIARY_TYPE_ID,
    AVIARY_TYPE_ID,
    AVIARY_TYPE_NAME,
    DOCUMENT_NAME
} from "./fieldNames";
import {int64FieldToZonedDateTime} from "../../time/time-utils";

export default TO => ({
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME],
    [AVIARY_DESCRIPTION]: TO[AVIARY_DESCRIPTION],
    [AVIARY_TYPE_ID]: {
        key: TO[AVIARY_TYPE_ID].id,
        values: TO[AVIARY_TYPE_ID].id,
        text: TO[AVIARY_TYPE_ID].name
    },
    [AVIARY_STATUS]: TO[AVIARY_STATUS],
    [AVIARY_REVISION_PERIOD]: TO[AVIARY_REVISION_PERIOD],
    [AVIARY_BUILT_DATE]: int64FieldToZonedDateTime(TO[AVIARY_BUILT_DATE]),
    [AVIARY_SQUARE]: TO[AVIARY_SQUARE],
    [AVIARY_CODE]: TO[AVIARY_CODE]
})
