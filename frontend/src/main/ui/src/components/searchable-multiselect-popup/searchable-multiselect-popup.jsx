import {Button, Container, Header, Icon, Input, List, ListItem, SegmentInline} from "semantic-ui-react";
import React, {useEffect, useState} from "react";
import _ from "lodash"

import './searchable-multiselect-popup.css'
import {Ref} from "@fluentui/react-component-ref";
import Popup from "reactjs-popup";

const SelectItem = React.forwardRef((props, ref) => {
    const {
        selected,
        onSelect,
        id,
        value,
        text
    } = props

    return (
        <Ref innerRef={ref}>
            <ListItem onClick={() => onSelect({id, value})} active={selected} content={text}/>
        </Ref>
    )
})

const textPredicateBuilder = re => ({text}) => re.test(text)

export const SearchableMultiselectPopup = props => {

    const {
        options = [],
        onSelect,
        onCancel,
        open,
        popup
    } = props

    const [selected, setSelected] = useState([])
    const [search, setSearch] = useState('')

    const optionsWithRefs = options.map(option => ({ref: React.createRef(), ...option}))

    const selectedIds = new Set(selected.map(entity => entity.id))

    const handleInput = (event, {value}) => setSearch(value)

    const handleCLick = () => onSelect && onSelect(selected.map(item => item.value))

    const handleClose = () => {
        setSelected([])
        onCancel && onCancel()
    }

    const toggle = item => {
        const ids = selected.map(item => item.id)
        const index = ids.indexOf(item.id)
        if (index < 0) {
            setSelected([...selected, item])
        } else {
            setSelected([...selected.slice(0, index), ...selected.slice(index+1)])
        }
    }

    useEffect(() => {
        if (search === '') return
        const re = new RegExp(_.escapeRegExp(search), 'i')
        const firstFound = _.find(optionsWithRefs, textPredicateBuilder(re))
        if (typeof firstFound !== "undefined" && firstFound.ref.current) {
            firstFound.ref.current.scrollIntoView({
                behavior: "smooth"
            })
            firstFound.ref.current.focus()
        }
    }, [search])

    return (
        <Popup open={open} closeOnDocumentClick onClose={handleClose} {...popup}>
            <Container className={'searchable-multiselect-popup'}>
                <Container className={'searchable-multiselect-popup__header'} textAlign={'center'}>
                    <Header className={'searchable-multiselect-popup__title'} as={'h4'}>Test</Header>
                    <button className={'searchable-multiselect-popup__close'} type={'button'} onClick={handleClose}/>
                </Container>
                <List selection className={'searchable-multiselect-popup__options'}>
                    {
                        optionsWithRefs.map(option => (
                            <SelectItem key={option.id} ref={option.ref} selected={selectedIds.has(option.id)}
                                        onSelect={toggle} {...option}/>
                        ))
                    }
                </List>
                <Input icon={'search'} onChange={_.debounce(handleInput, 300)}/>
                <Container textAlign={'center'} className={'searchable-multiselect-popup__select'}>
                    <Button fluid type={'button'} onClick={handleCLick}>Select</Button>
                </Container>
            </Container>
        </Popup>
    )

}