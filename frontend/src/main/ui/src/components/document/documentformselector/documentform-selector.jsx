import React, {useState} from "react";
import {useDispatch} from "react-redux";
import {createDocument} from "../createdocument/popup/create-document-popup-slice";
import {Button, Table, TableBody, TableCell, TableHeader, TableHeaderCell, TableRow} from "semantic-ui-react";
import {useTranslation} from "react-i18next";

const TypeRow = ({text, onClick, active}) => (
    <TableRow onClick={onClick} active={active}>
        <TableCell>{text}</TableCell>
    </TableRow>
)

export const DocumentFormSelector = ({onSubmit, onCancel, selected = '', values, ...props}) => {
    const [selectedType, setSelectedType] = useState(selected)
    const {t} = useTranslation()

    const dispatch = useDispatch()

    const onTypeSubmit = () => {
        if (typeof selectedType !== 'undefined' && selectedType !== '') {
            onSubmit(selectedType)
            dispatch(createDocument(selectedType))
        }
    }

    return (
        <>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHeaderCell>{t("crm.document.formselector.title")}</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {values.map((val) => (
                        <TypeRow key={val} onClick={() => setSelectedType(val)} active={val === selectedType} text={t(`crm.document.type.${val}`)}/>
                    ))}
                </TableBody>
            </Table>
            <Button positive disabled={selectedType === ''} type={'button'} onClick={onTypeSubmit}>{t('crm.document.formselector.select')}</Button>
            <Button negative type={'button'} onClick={onCancel}>{t('crm.document.formselector.cancel')}</Button>
        </>
    )
}