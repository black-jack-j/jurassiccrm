import {AVIARY_TYPE_ID} from "./fieldNames";

export default form => ({
    ...form,
    aviaryTypeId: form[AVIARY_TYPE_ID].value
})