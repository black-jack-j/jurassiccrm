import {CreateDocumentForm} from "./create-document-form";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";
import {Grid, GridColumn, GridRow, Header} from "semantic-ui-react";
import {CreateDocumentDocumentTypeEnum as DocumentTypeEnum} from "../../generatedclient/apis";

export default {
    title: 'Create Document Forms',
    components: [CreateDocumentForm],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

export const Forms = () => (
    <Grid columns={3}>
        <GridRow>
            <GridColumn  width={5}>
                <CreateDocumentForm
                    documentType={DocumentTypeEnum.DinosaurPassport}
                    onSubmit={console.log}
                    onCancel={console.log}
                />
            </GridColumn>
            <GridColumn  width={6}>
                <CreateDocumentForm
                    documentType={DocumentTypeEnum.AviaryPassport}
                    onSubmit={console.log}
                    onCancel={console.log}
                />
            </GridColumn>
            <GridColumn  width={5}>
                <CreateDocumentForm
                    documentType={DocumentTypeEnum.TechnologicalMap}
                    onSubmit={console.log}
                    onCancel={console.log}
                />
            </GridColumn>
        </GridRow>
        <GridRow>
            <GridColumn  width={5}>
                <CreateDocumentForm
                    documentType={DocumentTypeEnum.ThemeZoneProject}
                    onSubmit={console.log}
                    onCancel={console.log}
                />
            </GridColumn>
            <GridColumn  width={6}>
                <CreateDocumentForm
                    documentType={DocumentTypeEnum.ResearchData}
                    onSubmit={console.log}
                    onCancel={console.log}
                />
            </GridColumn>
            <GridColumn  width={5}>
            </GridColumn>
        </GridRow>
    </Grid>
)