import React, {Fragment, useState} from 'react'
import {ApiProvider} from "../api";
import {fakeAPI} from "../fakeApi";
import withFormik from "storybook-formik";
import {ResearchEntitySearchContainer} from "./researchsearch/researchsearch-component-container";
import {SearchInputComponent} from "./search_input/searchinput-component";
import {Checkbox, Input as FormikInput} from "formik-semantic-ui-react";
import {useField} from "formik";
import {Dropdown} from "semantic-ui-react";

export default {
    title: 'Components',
    components: [ResearchEntitySearchContainer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        ),
        withFormik
    ]
}

const SearchTemplate = args => {
    const {user, research} = args
    return (
        <>
            <div>Research</div>
            <ResearchEntitySearchContainer {...research}/>
        </>
    )
}
export const DefaultSearchTemplate = SearchTemplate.bind({})

DefaultSearchTemplate.args = {
    user: {
        fieldName: 'user'
    },
    research: {
        fieldName: 'research'
    }
}

DefaultSearchTemplate.parameters = {
    formik: {
        initialValues: {
            user: undefined,
            research: undefined
        }
    }
}

const InputSearchTemplate = args => {
    const [isInput] = useField('isNew')

    return (
        <Fragment>
            <Checkbox name={'isNew'} />
            <SearchInputComponent isInput={isInput.value} {...args}/>
        </Fragment>
    )
}

export const DefaultInputSearchTemplate = InputSearchTemplate.bind({})

const Search = ResearchEntitySearchContainer

DefaultInputSearchTemplate.args = {
    Input: FormikInput,
    Search,
    input: {
        name: 'research.name'
    },
    search: {
        fieldName: 'research.id'
    }
}

DefaultInputSearchTemplate.parameters = {
    formik: {
        initialValues: {
            research: {
                name: '',
                id: undefined
            }
        }
    }
}

const _options = []

export const DropdownWithAddition = () => {

    const [currentValue, setCurrentValue] = useState('')
    const [options, setOptions] = useState(_options)

    const handleChange = (e, {value}) => {
        setCurrentValue(value)
    }

    const handleAddition = (e, {value}) => {
        setOptions([..._options, {text: value, value}])
        setCurrentValue(value)
    }

    return (
        <Dropdown
            options={options}
            placeholder={'Research'}
            search
            selection
            allowAdditions
            onChange={handleChange}
            onAddItem={handleAddition}
            value={currentValue}
        />
    )

}