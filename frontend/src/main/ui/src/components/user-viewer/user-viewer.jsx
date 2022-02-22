import React, {createRef, Suspense, useContext} from "react";
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
    Icon,
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
import UserContext from "../../user/user-context";
import {useDispatch} from "react-redux";
import {select} from "../edit-user-form-popup/edit-user-form-popup.slice";
import {AvatarEditorPopup} from "../avatar-editor-popup/avatar-editor-popup";
import ApiContext from "../../api";
import {resourceCache, useAsyncResource} from "use-async-resource";

export const UserViewer = props => {

    const {
        userReader,
        canAdd,
        onAdd,
        canEdit,
        onAvatarEdit
    } = props

    const {
        firstName,
        lastName,
        username,
        department,
        groups,
        avatarSrc
    } = userReader()

    const fileInputRef = createRef()

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
                        <img src={avatarSrc} alt={'user avatar'}/>
                        {canEdit && <Button type={'button'} size={'mini'} fluid content={'Change avatar'} onClick={onAvatarEdit}/>}
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

    const API = useContext(ApiContext)

    const {user} = useContext(UserContext)
    const dispatch = useDispatch()
    const [userReader, updateReader] = useAsyncResource(API.user.getUserById.bind(API.user), {userId})
    const ref = createRef()

    const canAdd = user.canEditUsers()
    const onAdd = () => dispatch(select(userId))
    const onAvatarEdit = () => ref.current.click()


    const updateAvatar = dataUrl => fetch(dataUrl).then(it => it.blob()).then(avatar => {
        resourceCache(API.user.getUserById).clear()
        return API.user.updateUserAvatar({userId, avatar})
    }).then(() => updateReader({userId})).catch(console.error)

    return (
        <Suspense fallback={'Loading...'}>
            <UserViewer
                userReader={() => userTOtoDisplay(userReader())}
                onAdd={onAdd}
                canAdd={canAdd}
                canEdit={canAdd}
                onAvatarEdit={onAvatarEdit}
            />
            <AvatarEditorPopup ref={ref} onChange={updateAvatar}/>
        </Suspense>
    )

}