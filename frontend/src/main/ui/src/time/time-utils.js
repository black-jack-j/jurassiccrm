import {DateTimeFormatter, Instant, ZonedDateTime, ZoneId} from "js-joda";

export const int64FieldToZonedDateTime = (millis) => (
    ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.SYSTEM)
)

export const DATE_FORMATTER = DateTimeFormatter.ofPattern('yyyy-MM-dd')
