import React, {Suspense, useContext} from "react";
import {
    Button,
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardMeta,
    Container,
    Grid,
    GridColumn,
    Header,
    Image,
    List,
    ListContent,
    ListDescription,
    ListHeader,
    ListItem,
    Menu,
    MenuItem,
    MenuMenu
} from "semantic-ui-react";
import {Avatar} from "../avatar/avatar";
import './user-viewer.css'
import {useUser} from "../../user/user";
import UserContext from "../../user/user-context";

export const UserViewer = props => {

    const {
        userReader,
        canAdd,
        onAdd
    } = props

    const {
        firstName,
        lastName,
        username,
        department,
        groups,
        avatarSrc
    } = userReader()


    return (
        <Container>
            <Menu secondary>
                <MenuItem>
                    <Header as={'h4'}>
                        Profile
                    </Header>
                </MenuItem>
                <MenuMenu position={'right'}>
                    {canAdd && <Button icon={'edit'} onClick={onAdd}/> }
                </MenuMenu>
            </Menu>
            <Grid columns={2}>
                <GridColumn width={6}>
                    <Card>
                        <Image src={avatarSrc} wrapped ui={false}/>
                        <CardContent>
                            <CardHeader>{`${firstName} ${lastName}`}</CardHeader>
                            <CardMeta>@{username}</CardMeta>
                            <CardDescription>{department}</CardDescription>
                        </CardContent>
                    </Card>
                </GridColumn>
                <GridColumn width={10}>
                    <Header as={'h5'}>Groups</Header>
                    <List className={'user-viewer__group_list'}>
                        {groups.map(group => (
                            <ListItem>
                                <Avatar src={group.avatarSrc}/>
                                <ListContent>
                                    <ListHeader>{group.name}</ListHeader>
                                    <ListDescription>{group.description}</ListDescription>
                                </ListContent>
                            </ListItem>
                        ))}
                    </List>
                </GridColumn>
            </Grid>
        </Container>
    )
}

const groupTOtoDisplay = ({id, name, description}) => ({
    avatarSrc: `/api/group/${id}/icon`,
    name,
    description
})

const userTOtoDisplay = ({id, firstName, lastName, username, groupIds, department}) => ({
    firstName,
    lastName,
    username,
    department,
    groups: groupIds.map(groupTOtoDisplay),
    avatarSrc: `/api/user/${id}/icon`
})

export const UserViewerContainer = props => {

    const {
        userId,
    } = props

    const {user} = useContext(UserContext)
    const userReader = useUser(userId)

    const canAdd = user.canEditUsers()

    return (
        <Suspense fallback={'Loading...'}>
            <UserViewer userReader={() => userTOtoDisplay(userReader())} canAdd={canAdd}/>
        </Suspense>
    )

}