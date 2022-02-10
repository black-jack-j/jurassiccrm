import React, {useContext, useState} from "react";
import ApiContext from "../../../api";
import {TaskTOTaskTypeEnum} from "../../../generatedclient/models";
import {FindAllByRolesAnyRolesEnum as Roles} from "../../../generatedclient/apis";
import {searchByNameOrUsernameBuilder} from "../../utilities/search-utils";
import {FormikEntitySearchComponent} from "../../usersearch/formik-entity-search-component";

const expectedRolesByTaskType = {
    [TaskTOTaskTypeEnum.Incubation]: [Roles.Admin, Roles.TaskWriter, Roles.IncubationTaskWriter],
    [TaskTOTaskTypeEnum.AviaryCreation]: [Roles.Admin, Roles.TaskWriter, Roles.AviaryBuildingTaskWriter],
    [TaskTOTaskTypeEnum.Research]: [Roles.Admin, Roles.TaskWriter, Roles.ResearchTaskWriter]
}

const assigneeToSearchResult = ({firstName, lastName, username, id}) => ({
    title: `${firstName} ${lastName}`,
    description: username,
    id
})

export const AssigneeSearchComponent = ({taskType, name,...props}) => {

    const [assignees, setAssignees] = useState([])
    const [isLoading, setLoading] = useState(false)

    const API = useContext(ApiContext)

    const expectedRoles = expectedRolesByTaskType[taskType]

    const searchAssignee = input => {
        const filter = searchByNameOrUsernameBuilder(input)
        setLoading(true)
        API.user.findAllByRolesAny({roles: expectedRoles})
            .then(filter)
            .then(setAssignees)
            .then(() => setLoading(false))
            .catch(console.error)
    }

    const searchResults = assignees.map(assigneeToSearchResult)

    return (
        <FormikEntitySearchComponent isLoading={isLoading}
                                     onSearch={searchAssignee}
                                     name={name}
                                     results={searchResults}
                                     {...props}/>
    )

}