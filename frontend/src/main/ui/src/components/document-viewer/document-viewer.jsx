import {useTranslation} from "react-i18next";
import {Button, Header, Label, LabelGroup, Menu, MenuItem, MenuMenu} from "semantic-ui-react";
import React, {Suspense, useContext} from "react";
import {UserInfoContainer} from "../userinfo/userinfo-container";
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../time/time-utils";
import UserContext from "../../user/user-context";
import {useDispatch, useSelector} from "react-redux";
import {updateDocument} from "../update-document-form-popup/update-document-form-popup-slice";
import {resourceCache, useAsyncResource} from "use-async-resource";
import {selectDocumentViewerState} from "./document-viewer-slice";

const getDocumentById = ({documentType, id}) => fetch(`/api/document/${documentType}/${id}`).then(it => it.json())

const EditMenu = ({onEdit}) => (
    <>
        <MenuItem>
            <Button icon={'edit'} onClick={onEdit}/>
        </MenuItem>
    </>
)


export const DocumentViewer = props => {

    const {
        name,
        documentType,
        author,
        lastUpdater,
        created,
        lastUpdated,
        description,
        refresh,
        canEdit,
        onEdit
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.document_viewer'})

    return (
        <>
            <Menu secondary text>
                <MenuItem>
                    <Header as={'h4'} content={name}/>
                </MenuItem>
                <MenuMenu position={'right'}>
                    <MenuItem>
                        <Button icon={'refresh'} onClick={refresh}/>
                    </MenuItem>
                    { canEdit && <EditMenu onEdit={onEdit} /> }
                </MenuMenu>
            </Menu>
            <LabelGroup>
                <Label>{documentType}</Label>
            </LabelGroup>
            <Header as={'h5'}>{t('author')}</Header>
            <UserInfoContainer id={author.id}/>
            <Header as={'h5'}>{t('created')}</Header>
            {created}
            <Header as={'h5'}>{t('lastUpdated')}</Header>
            <UserInfoContainer id={lastUpdater.id}/>
            <Header as={'h5'}>{t('lastUpdated')}</Header>
            {lastUpdated}
            <Header as={'h5'}>{t('description')}</Header>
            <p>
                {description}
            </p>
        </>
    )

}

export const DocumentViewerContainer = props => {

    const {
        documentReader,
        refresh
    } = props

    const {
        id,
        name,
        documentType,
        author,
        lastUpdater,
        description,
        created,
        lastUpdate
    } = documentReader()

    const formattedCreated = int64FieldToZonedDateTime(created).format(DATE_FORMATTER)
    const formattedLastUpdated = int64FieldToZonedDateTime(lastUpdate).format(DATE_FORMATTER)

    const {user} = useContext(UserContext)
    const dispatch = useDispatch()

    const canEdit = user.canEditDocumentOfType(documentType)
    const onEdit = () => dispatch(updateDocument({id, documentType}))
    const {t} = useTranslation()

    return (
        <DocumentViewer
            name={name}
            documentType={t(`crm.document.type.${documentType}`)}
            author={author}
            lastUpdater={lastUpdater}
            created={formattedCreated}
            lastUpdated={formattedLastUpdated}
            description={description}
            canEdit={canEdit}
            onEdit={onEdit}
            refresh={refresh}
        />
    )

}

const _SuspendableDocumentViewerContainer = props => {

    const {
        documentType,
        id
    } = props

    const [documentReader, updateReader] = useAsyncResource(getDocumentById, {documentType, id})

    const refresh = () => {
        resourceCache(getDocumentById).clear()
        updateReader({documentType, id})
    }

    return (
        <Suspense fallback={'Loading...'}>
            {documentType && <DocumentViewerContainer documentReader={documentReader} refresh={refresh}/>}
        </Suspense>
    )

}

export const SuspendableDocumentViewerContainer = props => {

    const {documentType, id} = useSelector(selectDocumentViewerState)
    const {t} = useTranslation()

    return (
        documentType ?
                <_SuspendableDocumentViewerContainer
                    documentType={documentType}
                    id={id}
                /> :
                <div>{t('crm.widget.document_viewer.empty')}</div>
    )

}