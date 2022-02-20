import {AVIARY_TYPE_ID} from "./fieldNames";

export default TO => ({
    ...TO,
    [AVIARY_TYPE_ID]: {
        value: TO.aviaryType.id,
        text: TO.aviaryType.name
    }
})