import {
    Button, Segment,
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

export const Container = (view) => (fieldName, name, componentProps) => {

    const Container = ({form, push, remove, ...props}) => {

        const items = (form.values && form.values[fieldName]) || []

        return (
            <Table compact={'very'}>
                <TableHeader fullWidth>
                    <TableRow >
                        <TableHeaderCell style={{height: 20}} colSpan={2} className={'center aligned'} width={16}>{name}</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    <Segment basic style={{overflow: 'auto', maxHeight: 150, height: 150}}>
                        {
                            items.map((item, index) => createContainerItem({
                                fieldName: `${fieldName}.${index}`,
                                view,
                                onDelete: () => remove(index),
                                props: componentProps
                            }))
                        }
                    </Segment>
                </TableBody>
                <TableFooter className={'center aligned'} fullWidth={true}>
                    <TableRow>
                        <TableHeaderCell colSpan={2} className={'center aligned'}>
                            <Button style={{width: 150}} type='button' onClick={() => push('')} icon='plus'/>
                        </TableHeaderCell>
                    </TableRow>
                </TableFooter>
            </Table>
        )
    }

    return Container

}