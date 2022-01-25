import {Input} from "formik-semantic-ui-react";
import {THEME_ZONE_PROJECT_MANAGER, THEME_ZONE_PROJECT_NAME} from "./fieldNames";
import React from "react";

export const ThemeZoneProjectSubform = props => {

    return (
        <>
            <Input name={THEME_ZONE_PROJECT_NAME} {...props}/>
            <Input name={THEME_ZONE_PROJECT_MANAGER} {...props}/>
        </>
    )

}