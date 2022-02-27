import {Grid, Button, CardGroup, GridColumn, Menu, MenuItem, Segment} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {Fragment} from "react";
import {DocumentCardContainer} from "../document-card/document-card-container";
import {EditableDocumentCardContainer} from "../editable-document-card/editable-document-card-container";
import {SuspendableDocumentViewerContainer} from "../document-viewer/document-viewer";

const mapDocsWithUser = (user, docs) => {

    return docs.map(doc => {
        if (user.canEditDocumentOfType(doc.documentType)) {
            return (
                <EditableDocumentCardContainer
                    key={doc.id}
                    type={doc.documentType}
                    id={doc.id}
                    name={doc.name}
                />
            )
        } else {
            return <DocumentCardContainer key={doc.id} type={doc.documentType} name={doc.name}/>
        }
    })

}

export const DocumentDashboard = ({items, loading, refresh, onAdd, currentUser,...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.dashboard'})

    const canAdd = currentUser && currentUser.canEditDocuments()

    return (
        <Fragment>
            <Menu text>
                <MenuItem header name={t('title')}/>
                {
                    canAdd &&
                    <MenuItem>
                        <Button icon={'plus'} onClick={onAdd}/>
                    </MenuItem>
                }
                <MenuItem>
                    <Button disabled={loading} icon={'refresh'} loading={loading} onClick={refresh}/>
                </MenuItem>
            </Menu>
            <Grid columns={2}>
                <GridColumn width={12}>
                    <CardGroup>{mapDocsWithUser(currentUser, items)}</CardGroup>
                </GridColumn>
                <GridColumn width={4}>
                    <Segment>
                        <SuspendableDocumentViewerContainer />
                    </Segment>
                </GridColumn>
            </Grid>
        </Fragment>
    )

}