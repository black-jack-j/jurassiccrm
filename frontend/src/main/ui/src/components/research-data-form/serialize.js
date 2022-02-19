import {
    DOCUMENT_NAME,
    RESEARCH_ATTACHMENT,
    RESEARCH_ATTACHMENT_NAME,
    RESEARCH_ID,
    RESEARCH_IS_NEW,
    RESEARCH_MATERIAL_DESCRIPTION,
    RESEARCH_NAME,
    RESEARCH_NAME_ID
} from "./fieldNames";
import {CreateDocumentDocumentTypeEnum as DocumentType} from "../../generatedclient/apis";

export default form => ({
    name: form[DOCUMENT_NAME],
    documentType: DocumentType.ResearchData,
    description: form[RESEARCH_MATERIAL_DESCRIPTION],
    researchNameId: {
        id: form[RESEARCH_NAME_ID][RESEARCH_ID],
        name: form[RESEARCH_NAME_ID][RESEARCH_NAME]
    },
    newResearch: form[RESEARCH_IS_NEW],
    attachmentName: form[RESEARCH_ATTACHMENT_NAME],
    attachment: form[RESEARCH_ATTACHMENT]
})