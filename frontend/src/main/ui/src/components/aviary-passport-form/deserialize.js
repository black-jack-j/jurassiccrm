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
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../time/time-utils";

export default TO => ({
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME],
    [AVIARY_DESCRIPTION]: TO[AVIARY_DESCRIPTION],
    [AVIARY_TYPE_ID]: {
        key: TO.aviaryType.id,
        value: TO.aviaryType.id,
        text: TO.aviaryType.name
    },
    [AVIARY_STATUS]: TO[AVIARY_STATUS],
    [AVIARY_REVISION_PERIOD]: TO[AVIARY_REVISION_PERIOD],
    [AVIARY_BUILT_DATE]: int64FieldToZonedDateTime(TO[AVIARY_BUILT_DATE]).format(DATE_FORMATTER),
    [AVIARY_SQUARE]: TO[AVIARY_SQUARE],
    [AVIARY_CODE]: TO[AVIARY_CODE]
})
