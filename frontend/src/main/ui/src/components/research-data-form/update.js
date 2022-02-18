import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";
import {RESEARCH_ATTACHMENT} from "./fieldNames";

export default API => documentId => values => {

    const researchData = {...values, documentType: DocumentTypeEnum.ResearchData}
    delete researchData[RESEARCH_ATTACHMENT]

    return API.document.updateResearchData({
        documentId,
        researchData: JSON.stringify(researchData),
        attachment: values[RESEARCH_ATTACHMENT]
    })

}