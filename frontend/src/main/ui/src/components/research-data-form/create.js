import {RESEARCH_ATTACHMENT} from "../document/form/create/researchdata/fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";

export const create = API => values => {

    const researchData = {...values, documentType: DocumentTypeEnum.ResearchData}
    delete researchData[RESEARCH_ATTACHMENT]

    return API.document.createResearchData({
        researchData: JSON.stringify(researchData), attachment: values[RESEARCH_ATTACHMENT]
    })
}