import {RESEARCH_ATTACHMENT} from "./fieldNames";
import serialize from "./serialize";

export const create = API => values => {

    const researchData = serialize(values)
    delete researchData[RESEARCH_ATTACHMENT]

    return API.document.createResearchData({
        researchData: JSON.stringify(researchData), attachment: values[RESEARCH_ATTACHMENT]
    })
}