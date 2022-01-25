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
import React from "react";

const createContainerItem = ({fieldName, view, onDelete, props}) => {

    return (
        <TableRow key={fieldName}>
            <TableCell width={15}>
                {view({fieldName, ...props})}
            </TableCell>
            <TableCell><Button type='button' icon='trash' onClick={onDelete}/></TableCell>
        </TableRow>
    )

}

export const Container = (view) => (fieldName, name, ...componentProps) => ({form, push, remove, ...props}) => {

    const items = form.values && form.values[fieldName]

    return (
        <Table>
            <TableHeader fullWidth>
                <TableHeaderCell colSpan={2} className={'center aligned'} width={16}>{name}</TableHeaderCell>
            </TableHeader>
            <TableBody>
                {
                    items.map((item, index) => createContainerItem({
                        fieldName: `${fieldName}.${index}`,
                        view,
                        onDelete: () => remove(index),
                        props: componentProps
                    }))
                }
            </TableBody>
            <TableFooter className={'center aligned'} fullWidth={true}>
                <TableRow>
                    <TableHeaderCell colSpan={2} className={'center aligned'}>
                        <Button type='button' onClick={() => push('')} icon='plus'/>
                    </TableHeaderCell>
                </TableRow>
            </TableFooter>
        </Table>
    )

}