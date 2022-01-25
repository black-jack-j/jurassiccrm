import React from "react";

import {AviaryTypeApi, DinosaurTypeApi, DocumentApi, TaskApi, UserApi} from "./generatedclient/apis";
import {Configuration} from "./generatedclient";

const configuration = new Configuration({basePath: 'http://localhost:8080'})

const API = {
    task: new TaskApi(configuration),
    user: new UserApi(configuration),
    document: new DocumentApi(configuration),
    aviaryType: new AviaryTypeApi(configuration),
    dinosaurType: new DinosaurTypeApi(configuration)
}

const ApiContext = React.createContext(API)

export const ApiProvider = ApiContext.Provider

export default ApiContext