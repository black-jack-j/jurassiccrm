import {Responsive, WidthProvider} from "react-grid-layout";
import React from "react";

import {CRMHeader} from "../components/crm-header/crm-header";
import {CRMCommonsPanel} from "../components/crm-commons-panel/crm-commons-panel";
import {CrmSecurityPanel} from "../components/crm-security-panel/crm-security-panel";
import {withCurrentUser} from "../user/user-context";

const ResponsiveGridLayout = WidthProvider(Responsive)

const layouts = {
    xxl: [
        {i: "s", x: 0, y: 1, w: 5, h: 6, static: true},
        {i: "h", x: 0, y: 0, w: 12, h: 1, static: true},
        {i: "c", x: 5, y: 1, w: 7, h: 6, static: true}
    ],
    xl: [
        {i: "s", x: 0, y: 5, w: 12, h: 4, static: true},
        {i: "h", x: 0, y: 0, w: 12, h: 1, static: true},
        {i: "c", x: 0, y: 1, w: 12, h: 4, static: true}
    ]
}

const cols = {
    xxl: 12,
    xl: 12
}

const breakpoints = {
    xxl: 2000,
    xl: 1200
}

const _Workspace = () => {


    return (
        <ResponsiveGridLayout autoSize={false} cols={cols} className={'layout'} layouts={layouts} breakpoints={breakpoints}>
            <div key={"h"}>
                <CRMHeader/>
            </div>
            <div key="s">
                <CrmSecurityPanel />
            </div>
            <div key="c">
                <CRMCommonsPanel />
            </div>
        </ResponsiveGridLayout>
    )

}

export const Workspace = withCurrentUser(_Workspace)