import React from "react";

import {
    AviaryApi,
    DecorationTypeApi,
    DinosaurApi,
    DocumentApi, GroupApi,
    LogsApi,
    ResearchApi, RoleApi,
    TaskApi,
    UserApi
} from "./generatedclient/apis";
import {Configuration} from "./generatedclient";

const configuration = new Configuration({basePath: 'http://localhost:8080'})

const API = {
    task: new TaskApi(configuration),
    user: new UserApi(configuration),
    document: new DocumentApi(configuration),
    aviary: new AviaryApi(configuration),
    dinosaur: new DinosaurApi(configuration),
    decorationType: new DecorationTypeApi(configuration),
    research: new ResearchApi(configuration),
    log: new LogsApi(configuration),
    role: new RoleApi(configuration),
    group: new GroupApi(configuration)
}

const ApiContext = React.createContext(API)

export const ApiProvider = ApiContext.Provider

export default ApiContext