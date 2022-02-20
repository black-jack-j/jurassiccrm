import React from 'react';
import {Button, CardGroup, Grid, GridColumn, GridRow, Header, Menu, MenuItem} from "semantic-ui-react";
import {TaskCardContainer} from "./task-card-container";
import {useTranslation} from "react-i18next";

export const TaskDashboard = ({tasks, loading, refresh, onAdd, currentUser,...props}) => {

    const {t} = useTranslation('translation', {keyPrefix: 'crm.task.dashboard'})

    return (
        <>
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
            <CardGroup>{tasks.map((task) => <TaskCardContainer {...task} />)}</CardGroup>
        </>
    )
}