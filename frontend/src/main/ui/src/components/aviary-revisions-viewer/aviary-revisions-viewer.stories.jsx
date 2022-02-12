import {AviaryRevisionsViewer} from "./aviary-revisions-viewer";
import {ApiProvider} from "../../api";
import {fakeAPI} from "../../fakeApi";
import React from "react";
import _ from "lodash"
import {Instant, ChronoUnit} from "js-joda";

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
        {revisionDate: Instant.now().toEpochMilli(), aviary: {id: 1, name: 'Aviary 1'}}
    ]
}

export const TwoEntryRevisionViewer = Template.bind({})

TwoEntryRevisionViewer.args = {
    revisions: [
        {revisionDate: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(), aviary: {id: 1, name: 'Should be first'}},
        {revisionDate: Instant.now().toEpochMilli(), aviary: {id: 2, name: 'Should be second'}}
    ]
}

export const MultipleEntriesRevisionViewer = Template.bind({})

const baseDate = Instant.now()
const template = "Aviary #"

const getIthRevision = i => ({revisionDate: baseDate.plus(i, ChronoUnit.DAYS).toEpochMilli(), aviary: {id: i, name: `${template} ${i++}`}})

MultipleEntriesRevisionViewer.args = {
    revisions: _.range(1, 10).map(getIthRevision)
}