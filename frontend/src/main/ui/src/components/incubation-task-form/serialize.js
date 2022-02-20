import {DINOSAUR_TYPE_ID} from "./fieldNames";

export default form => ({
    ...form,
    dinosaurTypeId: form[DINOSAUR_TYPE_ID].value
})