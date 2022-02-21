import React, {createRef, useContext, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import UserContext from "../../user/user-context";
import ApiContext from "../../api";
import {useDispatch} from "react-redux";
import {open} from "../create-task-form-popup/create-task-popup-slice";
import {Ref} from "@fluentui/react-component-ref";
import {Button, Grid, GridColumn, Menu, MenuItem, Segment, Sticky, TabPane} from "semantic-ui-react";
import {TaskDashboard} from "../task-dashboard/task-dashboard";
import {Viewer} from "../task/viewer/viewer";
import {CreateTaskPopup} from "../create-task-form-popup/create-task-popup";
import {EditTaskPopup} from "../task/editor/edit-task-popup";

export const TaskPane = () => {

    const context = createRef()

    const {t} = useTranslation('translation', {keyPrefix: 'crm.task.dashboard'})

    const {user} = useContext(UserContext)

    const canAddTask = user && user.canEditTasks()

    const API = useContext(ApiContext)

    const [tasks, setTasks] = useState([])
    const [loading, setLoading] = useState(false)
    const dispatch = useDispatch()

    const refresh = () => {
        setTasks([])
        setLoading(true)
        API.task.getTasks().then(tasks => {
            setTasks(tasks)
            setLoading(false)
        })
    }

    useEffect(() => {
        refresh()
    }, [])

    const handleAdd = () => dispatch(open())

    return (
        <Ref innerRef={context}>
            <TabPane className={'pane'} attached={false} style={{overflow: "auto", height: "100%"}}>
                <Menu text>
                    <MenuItem header name={t('title')}/>
                    {canAddTask &&
                    <MenuItem>
                        <Button icon={'plus'} onClick={handleAdd}/>
                    </MenuItem>
                    }
                    <MenuItem>
                        <Button icon={'refresh'} disabled={loading} loading={loading} onClick={refresh}/>
                    </MenuItem>
                </Menu>
                <Grid columns={2}>
                    <GridColumn width={12}>
                        <TaskDashboard tasks={tasks}/>
                    </GridColumn>
                    <GridColumn width={4}>
                        <Sticky context={context} offset={70}>
                            <Segment>
                                <Viewer />
                            </Segment>
                        </Sticky>
                    </GridColumn>
                </Grid>
                <CreateTaskPopup />
                <EditTaskPopup />
            </TabPane>
        </Ref>

    )

}

export const TaskTabMenu = () => {

    const {t} = useTranslation()

    return (
        <MenuItem key={'task'}>
            {t('crm.tab.task.name')}
        </MenuItem>
    )

}