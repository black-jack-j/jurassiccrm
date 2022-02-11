import {AviaryRevisionsViewer} from "./aviary-revisions-viewer";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";
import {LocalDate} from "js-joda";
import _ from "lodash"

export default {
    title: 'Aviary Revisions Viewer',
    components: [AviaryRevisionsViewer],
    decorators: [
        Story => (
            <ApiProvider value={fakeAPI}>
                <Story/>
            </ApiProvider>
        )
    ]
}

const Template = args => <AviaryRevisionsViewer {...args}/>

export const EmptyRevisionViewer = Template.bind({})
EmptyRevisionViewer.args = {
    revisions: []
}

export const SingleEntryRevisionViewer = Template.bind({})

SingleEntryRevisionViewer.args = {
    revisions: [
        {revisionDate: LocalDate.now(), aviary: {id: 1, name: 'Aviary 1'}}
    ]
}

export const TwoEntryRevisionViewer = Template.bind({})

TwoEntryRevisionViewer.args = {
    revisions: [
        {revisionDate: LocalDate.now().minusDays(10), aviary: {id: 1, name: 'Should be first'}},
        {revisionDate: LocalDate.now(), aviary: {id: 2, name: 'Should be second'}}
    ]
}

export const MultipleEntriesRevisionViewer = Template.bind({})

const baseDate = LocalDate.now()
const template = "Aviary #"

const getIthRevision = i => ({revisionDate: baseDate.plusDays(i), aviary: {id: i, name: `${template} ${i++}`}})

MultipleEntriesRevisionViewer.args = {
    revisions: _.range(1, 10).map(getIthRevision)
}