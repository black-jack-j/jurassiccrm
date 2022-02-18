import {Button, CardGroup, Menu, MenuItem} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {Fragment} from "react";
import {DocumentCardContainer} from "../document-card/document-card-container";

const mapToCard = item => (
    <DocumentCardContainer key={item.id} {...item}/>
)

export const DocumentDashboard = ({items, loading, refresh, onAdd,...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.dashboard'})

    return (
        <Fragment>
            <Menu text>
                <MenuItem header name={t('title')}/>
                <MenuItem>
                    <Button icon={'plus'} onClick={onAdd}/>
                </MenuItem>
                <MenuItem>
                    <Button active={!loading} loading={loading} onClick={refresh}>
                        {t('refresh')}
                    </Button>
                </MenuItem>
            </Menu>
            <CardGroup>{items.map(mapToCard)}</CardGroup>
        </Fragment>
    )

}