import {Button, CardGroup, Menu, MenuItem} from "semantic-ui-react";
import {useTranslation} from "react-i18next";
import React, {Fragment, useState} from "react";
import {DocumentCardContainer} from "../card/document-card-container";

const mapToCard = item => (
    <DocumentCardContainer key={item.id} {...item}/>
)

export const DocumentDashboard = ({items, loading, refresh,...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.document.dashboard'})

    return (
        <Fragment>
            <Menu text>
                <MenuItem header name={t('title')}/>
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