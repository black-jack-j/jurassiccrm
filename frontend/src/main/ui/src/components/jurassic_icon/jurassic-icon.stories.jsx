import {UserIcon} from "./user/user-icon";
import React from "react";
import './jurassic-icon.css'

export default {
    title: 'UserIcon Avatar',
    components: [UserIcon]
}

export const Default = () => <div>
    <UserIcon circular className={'jurassic_icon-size_middle'}/>
</div>