import {Button, CardGroup, Menu, MenuItem} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {Fragment, useContext} from "react";
import {DocumentCardContainer} from "../document-card/document-card-container";
import UserContext from "../../user/user-context";
import {EditableDocumentCardContainer} from "../editable-document-card/editable-document-card-container";

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
                    <Button active={!loading} loading={loading} onClick={refresh}>
                        {t('refresh')}
                    </Button>
                </MenuItem>
            </Menu>
            <CardGroup>{mapDocsWithUser(currentUser, items)}</CardGroup>
        </Fragment>
    )

}