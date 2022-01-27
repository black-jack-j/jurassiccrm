import React from 'react'
import _ from 'lodash'

import {SearchComponent} from "./search/search-component";
import {EntitySearchComponent} from "./entitysearch/entity-search-component";
import {Grid, GridColumn, Header, Segment} from "semantic-ui-react";
import withFormik from "storybook-formik";
import {FormikSearchComponent} from "./usersearch/formik-search-component";
import {FormikBasketComponent} from "./basket/formik-basket-component";
import {Container} from "./container/container";
import {FieldArray} from "formik";

export default {
    title: 'Components',
    components: [SearchComponent, EntitySearchComponent],
    decorators: [withFormik]
}

const results = [
    {id: 0, name: 'ATest', username: 'DUser'},
    {id: 1, name: 'BTest', username: 'CUser'},
    {id: 2, name: 'CTest', username: 'BUser'},
    {id: 3, name: 'DTest', username: 'AUser'}

]

const value = results[1].username

const onResultSelectChange = result => console.log(result)
const onSearchChange = search => console.log(search)

const Template = args => <SearchComponent {...args}/>

const EntityTemplate = args => <Grid>
    <GridColumn width={6}>
        <EntitySearchComponent {...args}/>
    </GridColumn>
    <GridColumn width={10}>
        <Segment>
            <Header>All entities</Header>
            <pre style={{overflowX: 'auto'}}>
                {JSON.stringify(results, null, 2)}
            </pre>
        </Segment>
    </GridColumn>
</Grid>

export const DefaultSearch = Template.bind({})

DefaultSearch.args = {
    results,
    value,
    onSearchChange,
    onResultSelectChange
}

const toResultsMapper = ({username, name, ...other}) => ({
    title: name,
    description: username,
    username,
    name,
    ...other
})

const isStartsWithDog = value => {
    const stringVal = value.toString()
    return stringVal.length > 0 && stringVal.indexOf('@') === 0
}

const searchByNamePredicateBuilder = re => ({name}) => re.test(name)
const searchByUsernamePredicateBuilder = re => ({username}) => re.test(username)

const searchByNameOrUsername = value => {
    if (isStartsWithDog(value)) {
        const re = new RegExp(_.escapeRegExp(value.substring(1)), 'i')
        return _.filter(results, searchByUsernamePredicateBuilder(re)).map(toResultsMapper)
    } else {
        const re = new RegExp(_.escapeRegExp(value), 'i')
        return _.filter(results, searchByNamePredicateBuilder(re)).map(toResultsMapper)
    }
}

const search = value => {
    const re = new RegExp(_.escapeRegExp(value), 'i')
    return _.filter(results, ({username}) => re.test(username)).map(toResultsMapper)
}

export const EntitySearch = EntityTemplate.bind({})
EntitySearch.args = {
    search: searchByNameOrUsername,
    onValueChange: console.log,
    entityFieldSelector: value => value.name
}

export const UserSearch = args => <FormikSearchComponent {...args}/>

UserSearch.args = {
    search: searchByNameOrUsername,
    entityFieldSelector: value => value.name,
    valueFieldSelector: value => value.id,
    fieldName: 'userId'
}

UserSearch.parameters = {
    formik: {
        initialValues: {
            userId: null
        }
    }
}

const countProps = {
    minVal: 1,
    value: 1
}

const itemProps = {
    options: [
        {key: 0, text: 'Test0', value: 'a'},
        {key: 1, text: 'Text1', value: 'b'},
        {key: 2, text: 'Text2', value: 'c'}
    ]
}

const BasketComponentContainer = Container(FormikBasketComponent)('test', 'Container', {item: itemProps})

export const DefaultFormikBasket = () => <FieldArray name={'test'} component={BasketComponentContainer}/>

DefaultFormikBasket.parameters = {
    formik: {
        initialValues: {
            test: [
            ]
        }
    }
}