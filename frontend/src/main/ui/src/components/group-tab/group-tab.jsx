import React, {useState} from "react";
import {Grid, GridColumn, MenuItem, TabPane} from "semantic-ui-react";
import {GroupsViewerContainer} from "../groups-viewer/groups-viewer-container";
import {useTranslation} from "react-i18next";
import {CreateGroupFormPopup} from "../create-group-form-popup/create-group-form-popup";
import {GroupFormInitialValues} from "../group-form/initialValues";
import {EditGroupFormPopup} from "../update-group-form-popup/update-group-form-popup";

export const GroupPane = () => {

    const [groupId, setGroupId] = useState(null)

    return (
        <TabPane className={'pane'} attached={false}>
            <Grid columns={2}>
                <GridColumn width={4}>
                    <GroupsViewerContainer  canAdd={true} onSelect={setGroupId} />
                </GridColumn>
                <GridColumn width={12}>
                    {groupId ? <div>groups viewer</div> : <div>Select group</div>}
                </GridColumn>
            </Grid>
            <CreateGroupFormPopup initialValues={GroupFormInitialValues}/>
            <EditGroupFormPopup
                Placeholder={() => <div>No group is selected</div>}
            />
        </TabPane>
    )
}

export const GroupTabMenu = () => {

    const {t} = useTranslation()

    return (
        <MenuItem key={'group'}>
            {t('crm.tab.group.name')}
        </MenuItem>
    )

}