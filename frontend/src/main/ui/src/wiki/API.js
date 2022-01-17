import axios from 'axios';

const API = axios.create({
    baseURL: `http://localhost:8080/api`
});
export default API;

const getAllTitles = () => API.get('/wiki/title');

const findByTitle = (title) => API.get(`/wiki?title=${title}`)

export {
    API,
    getAllTitles,
    findByTitle
}