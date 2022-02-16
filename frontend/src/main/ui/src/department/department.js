import {UserOutputTODepartmentEnum as DepartmentEnum} from "../generatedclient/models";

export const useDepartments = () => {

    const refresh = () => {}

    const departments = Object.values(DepartmentEnum)

    return [{departments, state: 'loaded'}, refresh]

}