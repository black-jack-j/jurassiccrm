import {UserViewer, UserViewerContainer} from "./user-viewer";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";

export default {
    title: 'UserViewer',
    components: [UserViewer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

const Template = args => <UserViewer {...args}/>

export const Default = Template.bind({})

Default.args = {
    user: {
        id: 1,
        firstName: 'Boris',
        lastName: 'Yurinov',
        username: 'blade',
        department: 'Security',
        avatarSrc: 'https://cs8.pikabu.ru/post_img/big/2016/09/24/9/147472900315631641.jpg',
        groups: [
            {id: 1, name: 'Snatch', avatarSrc: 'https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/2d213f77-3c09-45ac-9d97-8c479d18627b/600x900', description: 'Some description'},
            {id: 2, name: 'Test1', description: 'Some description'},
            {id: 3, name: 'Test3', description: 'Some description'}
        ]
    }
}

export const Container = () => {
    return <UserViewerContainer userId={1}/>
}