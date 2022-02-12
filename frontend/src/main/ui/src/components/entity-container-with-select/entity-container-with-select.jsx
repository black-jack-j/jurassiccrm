import {
    Button,
    Table,
    TableBody,
    TableCell,
    TableFooter,
    TableHeader,
    TableHeaderCell,
    TableRow
} from "semantic-ui-react";
import React, {useState} from "react";
import {SearchableMultiselectPopup} from "../searchable-multiselect-popup/searchable-multiselect-popup";
import './entity-container-with-select.css'

const ContainerItem = ({text, onDelete}) => {

    return (
        <TableRow className={'entity-container-with-select__item'}>
            <TableCell width={15}>
                {text}
            </TableCell>
            <TableCell><Button type='button' icon='trash' onClick={onDelete}/></TableCell>
        </TableRow>
    )

}

export const EntityContainerWithSelect = props => {

    const {
        title,
        items = [],
        remove,
        push,
        options = []
    } = props

    const [open, setOpen] = useState(false)

    const openSelector = () => setOpen(true)
    const closeSelector = () => setOpen(false)
    const handleSelect = selected => {
        push(selected)
        closeSelector()
    }

    return (
        <div className={'entity-container-with-select'}>
            <Table className={'entity-container-with-select__table'}>
                <TableHeader fullWidth>
                    <TableRow >
                        <TableHeaderCell style={{height: 20}} colSpan={2} className={'center aligned'} width={16}>{title}</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                    <TableBody className={'entity-container-with-select__body'}>
                        {
                            items.map((item, index) => <ContainerItem key={item.id} text={item.text} onDelete={() => remove(index)}/>)
                        }
                    </TableBody>
                <TableFooter className={'center aligned'} fullWidth={true}>
                    <TableRow>
                        <TableHeaderCell colSpan={2} className={'center aligned'}>
                            <Button style={{width: 150}} type='button' onClick={openSelector} icon='plus'/>
                            <SearchableMultiselectPopup options={options} onSelect={handleSelect} onCancel={closeSelector} open={open}/>
                        </TableHeaderCell>
                    </TableRow>
                </TableFooter>
            </Table>
        </div>
    )

}