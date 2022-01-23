import ReactDOM from "react-dom";
import React from "react";
import {CRM} from "./crm";
import {Provider} from "react-redux";
import store from "./store/store";


const rootElement = document.getElementById("root");
ReactDOM.render(
    <Provider store={store}>
        <CRM/>
    </Provider>,
    rootElement
);