import {TaskApi, UserApi} from "./generatedclient/apis";
import {Configuration} from "./generatedclient";

const configuration = new Configuration({basePath: 'http://localhost:8080'})

export const API = {
    task: new TaskApi(configuration),
    user: new UserApi(configuration)
}