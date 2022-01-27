import {DocumentCard} from "./document-card";
import React from "react";
import {useDispatch} from "react-redux";

export const DocumentCardContainer = props => {

    const dispatch = useDispatch()


    return (
        <DocumentCard onSelect={} {...props} />
    )

}