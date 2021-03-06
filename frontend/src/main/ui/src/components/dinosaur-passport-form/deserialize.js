import {
    DINOSAUR_DESCRIPTION,
    DINOSAUR_HEIGHT,
    DINOSAUR_INCUBATION_DATE,
    DINOSAUR_NAME,
    DINOSAUR_REVISION_PERIOD,
    DINOSAUR_STATUS,
    DINOSAUR_TYPE_ID,
    DINOSAUR_WEIGHT,
    DOCUMENT_NAME
} from "./fieldNames";
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../time/time-utils";

export default TO => ({
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME],
    [DINOSAUR_TYPE_ID]: {
        key: TO.dinosaurType ? TO.dinosaurType.id : '',
        value: TO.dinosaurType ? TO.dinosaurType.id : '',
        text: TO.dinosaurType ? TO.dinosaurType.name : ''
    },
    [DINOSAUR_INCUBATION_DATE]: int64FieldToZonedDateTime(TO[DINOSAUR_INCUBATION_DATE]).format(DATE_FORMATTER),
    [DINOSAUR_NAME]: TO[DINOSAUR_NAME],
    [DINOSAUR_WEIGHT]: TO[DINOSAUR_WEIGHT],
    [DINOSAUR_HEIGHT]: TO[DINOSAUR_HEIGHT],
    [DINOSAUR_REVISION_PERIOD]: TO[DINOSAUR_REVISION_PERIOD],
    [DINOSAUR_STATUS]: TO[DINOSAUR_STATUS],
    [DINOSAUR_DESCRIPTION]: TO[DINOSAUR_DESCRIPTION]

})