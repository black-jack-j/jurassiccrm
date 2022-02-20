import {RESEARCH_ATTACHMENT} from "./fieldNames";
import serialize from "./serialize";

export default API => documentId => values => {

    const researchData = serialize(values)
    delete researchData[RESEARCH_ATTACHMENT]

    return API.document.updateResearchData({
        documentId,
        researchData: JSON.stringify(researchData),
        attachment: values[RESEARCH_ATTACHMENT]
    })

}