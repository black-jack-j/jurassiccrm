import axios from "axios";

const API = axios.create({
    baseURL: 'http://localhost:8080/api/task'
})

export default API

const getAllTasks = () => API.get('').then(response => response.data);
const getAllAssignees = () => API.get('/assignee').then(response => response.data)
const getAllTaskTypes = () => API.get('/type').then(response => response.data)

const create = ({taskType, taskName, ...params}) => {
    const requestBody = {...params, taskType, name: taskName, additionalParams: {...params}}
    return API.post(`/${taskType}`, requestBody).then(response => response.data)
}
export {
    getAllTasks,
    getAllAssignees,
    getAllTaskTypes,
    create
}