import React from 'react'

import {Selector} from './selector/Selector'
import {withFormik} from "storybook-formik";
import {Input} from "./input/Input";
import {TextArea} from "./textarea/TextArea";

export default {
    title: 'Components',
    decorators: [withFormik],
    components: [Selector, Input, TextArea]
}

const typeDict = {
    'INCUBATION': 'Заявка на инкубацию',
    'RESEARCH': 'Заявка на исследование',
    'AVIARY_CREATION': 'Заявка на создание вольера'
}

export const components = () => (
    <>
        <Selector name="taskType" label="task type">
            {dictionaryToListOfOptions(typeDict)}
        </Selector>
        <br/>
        <Input name="taskName" placeholder={'Название заявки'}/>
        <br/>
        <Input name="assigneeId" placeholder={'Исполнитель'}/>
        <br/>
        <Selector name="priority" placeholder={'Приоритет'}/>
        <br/>
        <Input name="taskGoal" placeholder={'Цель исследования'}/>
        <br/>
        <TextArea name="taskDescription" placeholder={'Описание'}/>
    </>
)

components.parameters = {
    formik: {
        initialValues: {
            taskType: 'INCUBATION',
        }
    }
}

const dictionaryToListOfOptions = dictionary => (
    Object.entries(dictionary).map(getOption)
)

const getOption = entry => <option value={entry[0]}>{entry[1]}</option>