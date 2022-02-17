import {Responsive, WidthProvider} from "react-grid-layout";
import React from "react";

import './workspace.css'
import {CRMHeader} from "../components/crm-header/crm-header";
import {CRMCommonsPanel} from "../components/crm-commons-panel/crm-commons-panel";
import {CrmSecurityPanel} from "../components/crm-security-panel/crm-security-panel";

const ResponsiveGridLayout = WidthProvider(Responsive)

const layouts = {
    xxl: [
        {i: "a", x: 0, y: 1, w: 5, h: 6, static: true},
        {i: "h", x: 0, y: 0, w: 12, h: 1, static: true},
        {i: "b", x: 5, y: 1, w: 7, h: 6, static: true}
    ]
}


export const Workspace = () => {


    return (
        <ResponsiveGridLayout autoSize={false} cols={{xxl: 12}} className={'layout'} layouts={layouts} breakpoints={{xxl: 2000}}>
            <div key={"h"}>
                <CRMHeader avatarSrc={''}/>
            </div>
            <div key="a">
                <CrmSecurityPanel />
            </div>
            <div key="b">
                <CRMCommonsPanel />
            </div>
        </ResponsiveGridLayout>
    )

}