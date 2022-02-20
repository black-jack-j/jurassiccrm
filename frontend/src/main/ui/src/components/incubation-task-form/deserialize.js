import {DINOSAUR_TYPE_ID} from "./fieldNames";

export default TO => ({
    ...TO,
    [DINOSAUR_TYPE_ID]: {
        value: TO.dinosaurType.id,
        text: TO.dinosaurType.name
    }
})