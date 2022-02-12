import {EntityContainerWithSelect} from "./entity-container-with-select";
import React, {useState} from "react";
import _ from "lodash";

export default {
    title: 'Entity Container with Select',
    component: [EntityContainerWithSelect]
}

const Template = args => <EntityContainerWithSelect {...args}/>

export const Empty = Template.bind({})

Empty.args = {
    items: [],
    options: []
}

const getOptions = (n=1) => _.range(0, n).map(i => ({id: i, value: i, text: i}))

export const Interactive = () => {

    const [items, setItems] = useState([])
    const options = getOptions(10)

    const selectedIds = new Set(items.map(item => item.id))

    const push = selected => {
        setItems([...items, ...selected])
    }

    const remove = index => {
        setItems([...items.slice(0, index), ...items.slice(index+1)])
    }

    return (
        <EntityContainerWithSelect push={push}
                                   remove={remove}
                                   items={items}
                                   options={options.filter(option => !selectedIds.has(option.id))}
                                   title={'Test'}/>
    )

}