import {WidgetPane} from "./widget-pane";
import React from "react";
import {Segment} from "semantic-ui-react";

export default {
    title: 'Widget Pane',
    components: [WidgetPane]
}

export const EmptyWidgetPane = () => (
    <WidgetPane/>
)

export const SingleWidgetPane = () => (
    <WidgetPane>
        <Segment color={'blue'}/>
    </WidgetPane>
)

export const SingleColumnWidgetPane = () => (
    <WidgetPane>
        <Segment color={'blue'}/>
        <Segment color={'red'}/>
    </WidgetPane>
)

export const TwoColumnWidgetPane = () => (
    <WidgetPane cols={2}>
        <Segment color={'blue'}/>
        <Segment color={'red'}/>
    </WidgetPane>
)

export const ThreeColumnWidgetPane = () => (
    <WidgetPane cols={3}>
        <Segment color={'blue'}/>
        <Segment color={'red'}/>
        <Segment color={'orange'}/>
    </WidgetPane>
)