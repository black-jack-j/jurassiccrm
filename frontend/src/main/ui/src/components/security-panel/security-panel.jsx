import {Grid, GridColumn, GridRow, Tab} from "semantic-ui-react";
import React from "react";
import {LogViewerContainer} from "../logviewer/container/logviewer-container";
import {GroupsViewerContainer} from "../groups-viewer/groups-viewer-container";
import {CreateGroupFormPopup} from "../create-group-form-popup/create-group-form-popup";
import {GroupFormInitialValues} from "../group-form/initialValues";
import {EditGroupFormPopup} from "../update-group-form-popup/update-group-form-popup";
import {UsersViewerContainer} from "../users-viewer/users-viewer-container";
import {CreateUserForm} from "../create-user-form/create-user-form";
import {UserFormInitialValues} from "../user-form/initialValues";

const SecurityPanelContent = () => {
    return (
        <>
            <Grid columns={4}>
                <GridRow>
                    <GridColumn>
                        <LogViewerContainer />
                    </GridColumn>
                    <GridColumn>
                        <GroupsViewerContainer canAdd={true} />
                    </GridColumn>
                    <GridColumn>
                        <UsersViewerContainer />
                    </GridColumn>
                </GridRow>
                <GridRow>
                    <GridColumn>
                        <CreateUserForm
                            onSubmit={console.log}
                            onCancel={console.log}
                            initialValues={UserFormInitialValues}
                        />
                    </GridColumn>
                </GridRow>
            </Grid>
            <CreateGroupFormPopup initialValues={GroupFormInitialValues}/>
            <EditGroupFormPopup
                Placeholder={() => <div>No group is selected</div>}
                LoadingPlaceholder={() => <div>Loading...</div>}
                ErrorPlaceholder={() => <div>Error Occurred</div>}
            />
        </>
    )
}

export const SecurityPanel = () => <Tab.Pane><SecurityPanelContent/></Tab.Pane>