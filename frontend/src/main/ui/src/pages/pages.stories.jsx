import {Workspace} from "./workspace";
import {ApiProvider} from "../api";
import {fakeAPI} from "../fakeApi";
import React from "react";
import {Provider} from "react-redux";
import store from "../store/store";
import 'semantic-ui-css/semantic.min.css'

export default {
    title: 'Pages',
    components: [Workspace],
    decorators: [
        Story => (
            <Provider store={store}>
                <ApiProvider value={fakeAPI}>
                    <Story/>
                </ApiProvider>
            </Provider>
        )
    ]
}

export const WorkspacePage = () => (<Workspace />)