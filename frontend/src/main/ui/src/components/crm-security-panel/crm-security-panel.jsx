import {Grid, GridColumn, Segment, Tab, TabPane} from "semantic-ui-react";
import React, {useState} from "react";
import {LogViewerContainer} from "../logviewer/container/logviewer-container";
import {GroupsViewerContainer} from "../groups-viewer/groups-viewer-container";
import {UsersViewerContainer} from "../users-viewer/users-viewer-container";
import {UserViewerContainer} from "../user-viewer/user-viewer";
import {Responsive, WidthProvider} from "react-grid-layout/index";

import './crm-security-panel.css'
import {UserTabMenu} from "../user-tab/user-tab";
import {GroupTabMenu} from "../group-tab/group-tab";

const ResponsiveGridLayout = WidthProvider(Responsive)

const layout = {
    xxl: [
        {i: "a", x: 0, y: 0, w: 1, h: 4, static: true},
        {i: "b", x: 0, y: 4, w: 1, h: 2, static: true}
    ],
    xl: [
        {i: "a", x: 0, y: 0, w: 1, h: 4, static: true},
        {i: "b", x: 0, y: 4, w: 1, h: 2, static: true}
    ]
}

const UserPane = () => {

    const [userId, setUserId] = useState(null)

    return (
        <TabPane className={'pane'} attached={false}>
            <Grid columns={2}>
                <GridColumn width={4}>
                    <Segment piled>
                        <UsersViewerContainer onSelect={setUserId} />
                    </Segment>
                </GridColumn>
                <GridColumn width={12}>
                    <Segment>
                        {userId ? <UserViewerContainer userId={userId} /> : <div>Select user</div>}
                    </Segment>
                </GridColumn>
            </Grid>
        </TabPane>
    )

}

const GroupPane = () => {

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
        </TabPane>
    )
}

const userGroupPanes = [
    {menuItem: <UserTabMenu/>, render: () => <UserPane />},
    {menuItem: <GroupTabMenu/>, render: () => <GroupPane/>}
]


const SecurityPanelContent = () => {
    return (
        <>
            <ResponsiveGridLayout
                margin={[0, 0]}
                autoSize={false}
                cols={{xxl: 1, xl: 1}}
                className={'layout'}
                layouts={layout}
                breakpoints={{xxl: 2000, xl: 1200}}
            >
               <div key={"a"}>
                   <Tab className={'tab'} menu={{secondary: true, pointing: true}} panes={userGroupPanes}/>
               </div>
                <div key={"b"}>
                    <LogViewerContainer/>
                </div>
            </ResponsiveGridLayout>
        </>
    )
}

export const CrmSecurityPanel = () => <SecurityPanelContent/>