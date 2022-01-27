import ReactDOM from "react-dom";
import React, {Suspense} from "react";
import {CRM} from "./crm";
import {Provider} from "react-redux";
import store from "./store/store";


const rootElement = document.getElementById("root");
ReactDOM.render(
    <Suspense fallback="loading">
        <Provider store={store}>
            <CRM/>
        </Provider>
    </Suspense>,
    rootElement
);