import React from "react";

export const SearchInputComponent = ({Search, Input, isInput, search, input}) => {

        return isInput ? <Input {...input}/> : <Search {...search}/>

}