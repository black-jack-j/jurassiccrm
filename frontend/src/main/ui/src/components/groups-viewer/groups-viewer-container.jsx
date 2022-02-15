import {useGroups} from "../../group/group";
import React from "react";
import {GroupsViewer} from "./groups-viewer";
import {useDispatch} from "react-redux";
import {open as openNewGroupFormPopup} from "../create-group-form-popup/create-group-form-popup.slice"
import {select as selectGroup} from "../update-group-form-popup/update-group-form-popup.slice"

export const GroupsViewerContainer = ({canAdd}) => {

    const [groups, refresh] = useGroups()

    const dispatcher = useDispatch()

    const openNewGroupForm = () => dispatcher(openNewGroupFormPopup())
    const onSelect = groupId => dispatcher(selectGroup(groupId))

    return (
        <GroupsViewer
            groups={groups}
            canAdd={canAdd}
            onAdd={openNewGroupForm}
            onSelect={onSelect}
        />
    )

}