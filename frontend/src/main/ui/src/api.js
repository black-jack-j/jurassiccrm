import React from "react";

import {AviaryTypeApi, DecorationTypeApi, DinosaurApi, DocumentApi, TaskApi, UserApi} from "./generatedclient/apis";
import {Configuration} from "./generatedclient";

const configuration = new Configuration({basePath: 'http://localhost:8080'})

const API = {
    task: new TaskApi(configuration),
    user: new UserApi(configuration),
    document: new DocumentApi(configuration),
    aviaryType: new AviaryTypeApi(configuration),
    dinosaur: new DinosaurApi(configuration),
    decorationType: new DecorationTypeApi(configuration)
}

const ApiContext = React.createContext(API)

export const ApiProvider = ApiContext.Provider

export default ApiContext