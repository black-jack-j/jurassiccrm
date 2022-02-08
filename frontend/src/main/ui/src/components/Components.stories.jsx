import React, {Fragment, useState} from 'react'
import {UserSearchContainer} from "./usersearch/usersearch-component-container";
import {ApiProvider} from "../api";
import {fakeAPI} from "../fakeApi";
import withFormik from "storybook-formik";
import {ResearchSearchContainer} from "./researchsearch/researchsearch-component-container";
import {SearchInputComponent} from "./search_input/searchinput-component";
import {Checkbox, Input as FormikInput} from "formik-semantic-ui-react";
import {useField} from "formik";

export default {
    title: 'Components',
    components: [UserSearchContainer, ResearchSearchContainer],
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
            <div>User</div>
            <UserSearchContainer {...user}/>
            <div>Research</div>
            <ResearchSearchContainer {...research}/>
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

const Search = ResearchSearchContainer

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