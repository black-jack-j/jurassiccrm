import {SearchableMultiselectPopup} from "./searchable-multiselect-popup";
import React, {useState} from "react";
import _ from "lodash"
import {Button} from "semantic-ui-react";

export default {
    title: 'Searchable Multiselect',
    components: [SearchableMultiselectPopup]
}

const Template = args => <SearchableMultiselectPopup {...args}/>

export const Empty = Template.bind({})

Empty.args = {
    options: []
}

export const SingleElement = Template.bind({})

SingleElement.args = {
    options: [
        {id: 1, value: 10, text: 'Hi'}
    ]
}

export const MultipleElements = Template.bind({})

const getOptions = (n=1) => _.range(0, n).map(i => ({id: i, value: i, text: i}))

MultipleElements.args = {
    options: getOptions(10),
    onSelect: console.log
}

export const PopupSelect = () => {

    const [open, setOpen] = useState(false)


    return (
        <>
            <Button onClick={() => setOpen(!open)}>Select</Button>
            <SearchableMultiselectPopup options={getOptions(10)}
                                        onSelect={console.log}
                                        onCancel={() => setOpen(false)}
                                        title={'Test'}
                                        open={open}/>
        </>
    )

}

export const TwoPopups = () => {
    const [openA, setOpenA] = useState(false)
    const [openB, setOpenB] = useState(false)

    return (
        <>
            <Button onClick={() => setOpenA(!openA)}>SelectA</Button>
            <Button onClick={() => setOpenB(!openB)}>Select B</Button>
            <SearchableMultiselectPopup options={getOptions(10)}
                                        onSelect={console.log}
                                        onCancel={() => setOpenA(false)}
                                        open={openA}/>
            <SearchableMultiselectPopup options={getOptions(10)}
                                        onSelect={console.log}
                                        onCancel={() => setOpenB(false)}
                                        open={openB}/>
        </>
    )
}