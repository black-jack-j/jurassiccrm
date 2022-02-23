import {
    Button,
    Dropdown,
    DropdownItem,
    DropdownMenu,
    Header,
    Label,
    LabelGroup,
    Menu,
    MenuItem,
    MenuMenu
} from "semantic-ui-react";
import React, {Suspense, useContext} from "react";
import UserContext from "../../user/user-context";
import {useTranslation} from "react-i18next";
import {DATE_FORMATTER, int64FieldToZonedDateTime} from "../../time/time-utils";
import {useDispatch, useSelector} from "react-redux";
import {editTask} from "../task/editor/edit-task-popup-slice";
import {resourceCache, useAsyncResource} from "use-async-resource";
import {UserInfoContainer} from "../userinfo/userinfo-container";
import {selectTaskViewerTask} from "./viewer-slice";
import ApiContext from "../../api";

const getTaskById = (taskType, id) => fetch(`/api/task/${taskType}/${id}`).then(it => it.json())

const EditMenu = ({title, onEdit, possibleNextStates, onNextState}) => (
    <>
        <MenuItem>
            <Dropdown text={title} labeled button className={'icon'}>
                <DropdownMenu>
                    {possibleNextStates.map(state => (
                        <DropdownItem key={state} onClick={() => onNextState(state)}>{state}</DropdownItem>
                    ))}
                </DropdownMenu>
            </Dropdown>
        </MenuItem>
        <MenuItem>
            <Button icon={'edit'} onClick={onEdit}/>
        </MenuItem>
    </>
)

export const TaskViewer = props => {

    const {
        name,
        taskType,
        assigneeId,
        description,
        createdById,
        lastUpdaterId,
        created,
        lastUpdated,
        currentState,
        canEdit,
        possibleNextStates = [],
        onNextState,
        onEdit,
        refresh
    } = props

    const {t} = useTranslation('translation', {keyPrefix: 'crm.widget.task_viewer'})

    return (
        <>
            <Menu secondary text>
                <MenuItem>
                    <Header as={'h4'} content={name}/>
                </MenuItem>
                <MenuMenu position={'right'}>
                    <MenuItem>
                        <Button icon={'refresh'} onClick={refresh}/>
                    </MenuItem>
                        {canEdit &&
                            <EditMenu
                                title={t('nextStates')}
                                onEdit={onEdit}
                                possibleNextStates={possibleNextStates}
                                onNextState={onNextState}
                            />
                        }
                </MenuMenu>
            </Menu>
            <LabelGroup>
                <Label>{taskType}</Label>
                <Label>{currentState}</Label>
            </LabelGroup>
            <Header as={'h5'}>{t('assignee')}</Header>
            <UserInfoContainer id={assigneeId}/>
            <Header as={'h5'}>{t('createdBy')}</Header>
            <UserInfoContainer id={createdById}/>
            <Header as={'h5'}>{t('created')}</Header>
            {created}
            <Header as={'h5'}>{t('lastUpdater')}</Header>
            <UserInfoContainer id={lastUpdaterId}/>
            <Header as={'h5'}>{t('lastUpdated')}</Header>
            {lastUpdated}
            <p>
                {description}
            </p>
        </>

    )

}

export const TaskViewerContainer = props => {
    const {
        taskReader,
        refresh
    } = props

    const {
        id,
        name,
        taskType,
        assigneeId,
        description,
        createdById,
        lastUpdaterId,
        created,
        lastUpdated,
        currentState,
        possibleNextStates = []
    } = taskReader()

    const {t} = useTranslation()

    const formattedCreated = int64FieldToZonedDateTime(created).format(DATE_FORMATTER)
    const formattedLastUpdated = int64FieldToZonedDateTime(lastUpdated).format(DATE_FORMATTER)

    const {user} = useContext(UserContext)
    const dispatch = useDispatch()

    const canEdit = user.canEditTaskOfType(taskType)
    const onEdit = () => dispatch(editTask({id, type: taskType}))

    const API = useContext(ApiContext)

    const onNextState = state => API.task.changeStatus({taskId: id, taskState: state}).then(() => {
        refresh()
    }).catch(console.error)

    return (
        <TaskViewer
            name={name}
            taskType={t(`crm.task.type.${taskType}`)}
            assigneeId={assigneeId}
            description={description}
            createdById={createdById}
            lastUpdaterId={lastUpdaterId}
            created={formattedCreated}
            lastUpdated={formattedLastUpdated}
            currentState={currentState}
            canEdit={canEdit}
            onEdit={onEdit}
            onNextState={onNextState}
            possibleNextStates={possibleNextStates}
            refresh={refresh}
        />
    )
}

const _SuspendableTaskViewerContainer = props => {


    const {
        taskType,
        id
    } = props

    const [taskReader, updateReader] = useAsyncResource(getTaskById, taskType, id)

    const refresh = () => {
        resourceCache(getTaskById).clear()
        updateReader(taskType, id)
    }

    return (
        <Suspense fallback={'Loading...'}>
            { taskType && <TaskViewerContainer taskReader={taskReader} refresh={refresh} /> }
        </Suspense>
    )

}

export const SuspendableTaskViewerContainer = props => {

    const task = useSelector(selectTaskViewerTask)
    const {t} = useTranslation()

    return (
        task ?
            <_SuspendableTaskViewerContainer
                taskType={task.taskType}
                id={task.id}
            /> :
            <div>{t('crm.widget.task_viewer.empty')}</div>
    )

}