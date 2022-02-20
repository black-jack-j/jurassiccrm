import React from "react";
import {
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
    MenuItem
} from "semantic-ui-react";
import {Avatar} from "../avatar/avatar";
import './user-viewer.css'
import {useUser} from "../../user/user";

export const UserViewer = props => {

    const {
        user
    } = props

    const {
        firstName,
        lastName,
        username,
        department,
        groups,
        avatarSrc
    } = user


    return (
        <Container>
            <Menu secondary>
                <MenuItem>
                    <Header as={'h4'}>
                        Profile
                    </Header>
                </MenuItem>
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

    const [result] = useUser(userId)

    if (result.state === 'loaded') {
        const userToDisplay = userTOtoDisplay(result.user)
        return <UserViewer user={userToDisplay}/>
    } else if (result.state === 'loading'){
        return <div>Loading</div>
    }

}