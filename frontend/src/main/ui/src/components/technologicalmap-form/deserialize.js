import {DINOSAUR_EGG_CREATION_STEPS, DINOSAUR_INCUBATION_STEPS, DINOSAUR_TYPE_ID, DOCUMENT_NAME} from "./fieldNames";

export default TO => ({
    [DOCUMENT_NAME]: TO[DOCUMENT_NAME],
    [DINOSAUR_INCUBATION_STEPS]: TO[DINOSAUR_INCUBATION_STEPS],
    [DINOSAUR_EGG_CREATION_STEPS]: TO[DINOSAUR_EGG_CREATION_STEPS],
    [DINOSAUR_TYPE_ID]: {
        key: TO.dinosaurType ? TO.dinosaurType.id : '',
        value: TO.dinosaurType ? TO.dinosaurType.id : '',
        text: TO.dinosaurType ? TO.dinosaurType.name : ''
    }
})