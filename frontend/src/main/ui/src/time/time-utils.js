import {DateTimeFormatter, Instant, ZonedDateTime, ZoneId, LocalDate} from "js-joda";

export const int64FieldToZonedDateTime = (millis) => (
    ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.SYSTEM)
)

export const DATE_FORMATTER = DateTimeFormatter.ofPattern('yyyy-MM-dd')


export const stringDateToInstant = date => {
    return LocalDate.parse(date, DATE_FORMATTER)
        .atStartOfDay(ZoneId.SYSTEM)
        .toInstant()
}