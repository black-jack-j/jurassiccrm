import {Button, Grid, GridColumn, Menu, MenuItem, Segment, Sticky, Tab, TabPane} from "semantic-ui-react";
import {DocumentDashboardContainer} from "../document-dashboard/document-dashboard-container";
import React, {createRef, useContext, useEffect, useState} from "react";
import {Viewer} from "../task/viewer/viewer";
import {CreateTaskPopup} from "../create-task-form-popup/create-task-popup";
import {EditTaskPopup} from "../task/editor/edit-task-popup";
import {Ref} from "@fluentui/react-component-ref";
import {useTranslation} from "react-i18next";
import ApiContext from "../../api";
import {useDispatch} from "react-redux";
import {open} from "../create-task-form-popup/create-task-popup-slice";
import {TaskDashboard} from "../task-dashboard/task-dashboard";
import UserContext from "../../user/user-context";
import {SuspendableTaskViewerContainer} from "../task-viewer/task-viewer";

const TaskPane = () => {

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
                    <GridColumn width={10}>
                        <TaskDashboard tasks={tasks}/>
                    </GridColumn>
                    <GridColumn width={6}>
                        <Sticky context={context} offset={70}>
                            <Segment>
                                <SuspendableTaskViewerContainer />
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

const DocumentPane = () => {

    return (
        <TabPane className={'pane'}  attached={false}>
            <DocumentDashboardContainer />
        </TabPane>
    )

}

const taskDocumentsPanes = [
    {menuItem: 'Tasks', render: () => <TaskPane/>},
    {menuItem: 'Documents', render: () => <DocumentPane />}
]

export const CRMCommonsPanel = props => {

    return <Tab className={'tab'} menu={{secondary: true, pointing: true}} panes={taskDocumentsPanes}/>

}