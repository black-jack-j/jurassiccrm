import React, {useState} from "react";
import {MenuItem, Grid, GridColumn, Segment, TabPane} from "semantic-ui-react";
import {UsersViewerContainer} from "../users-viewer/users-viewer-container";
import {UserViewerContainer} from "../user-viewer/user-viewer";
import {useTranslation} from "react-i18next";
import {CreateUserFormPopup} from "../create-user-form-popup/create-user-form-popup";
import {UserFormInitialValues} from "../user-form/initialValues";
import {EditUserFormPopup} from "../edit-user-form-popup/edit-user-form-popup";

export const UserPane = () => {

    const [userId, setUserId] = useState(null)

    return (
        <TabPane className={'pane'} attached={false}>
            <Grid columns={2}>
                <GridColumn width={12}>
                    <Segment piled>
                        <UsersViewerContainer onSelect={setUserId} />
                    </Segment>
                </GridColumn>
                <GridColumn width={4}>
                    <Segment>
                        {userId ? <UserViewerContainer userId={userId} /> : <div>Select user</div>}
                    </Segment>
                </GridColumn>
            </Grid>
            <CreateUserFormPopup initialValues={UserFormInitialValues}/>
            <EditUserFormPopup/>
        </TabPane>
    )

}

export const UserTabMenu = () => {

    const {t} = useTranslation()

    return (
        <MenuItem key={'user'}>
            {t('crm.tab.user.name')}
        </MenuItem>
    )

}