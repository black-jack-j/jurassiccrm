import {Input, TextArea} from "formik-semantic-ui-react";
import React from "react";
import {RESEARCH_ATTACHMENT, RESEARCH_DESCRIPTION, RESEARCH_NAME} from "./fieldNames";
import {Checkbox} from "semantic-ui-react";

export const ResearchMaterialSubform = props => {

    return (
        <>
            <Input name={RESEARCH_NAME} {...props}/>
            <Checkbox label={"New research"}/>
            <Input name={RESEARCH_ATTACHMENT} type={"file"} {...props}/>
            <TextArea name={RESEARCH_DESCRIPTION} {...props}/>
        </>
    )

}