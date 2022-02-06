import {useField} from "formik";
import {SearchInputComponent} from "./searchinput-component";
import React from "react";

export const FormikSearchInputComponent = ({name, ...props}) => {
    const [value] = useField(name)
    return <SearchInputComponent isInput={value.value} {...props}/>
}