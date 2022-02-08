import React, {useContext, useEffect, useState} from "react";
import ApiContext from "../../../api";
import {LogViewerComponent} from "../logviewer-component";

export const LogViewerContainer = ({...props}) => {

    const [items, setItems] = useState([])

    const API = useContext(ApiContext)

    const refresh = () => {
        API.log.getLogs().then(setItems)
    }

    useEffect(() => {
        refresh()
    }, [])

    return <LogViewerComponent items={items} refresh={refresh}/>

}