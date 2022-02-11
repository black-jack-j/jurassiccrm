import {Container, Grid, GridColumn, GridRow} from "semantic-ui-react";
import React from "react";

const reshape = (array, cols) => {
    const result = []
    while (array.length) {
        result.push(array.splice(0, cols))
    }
    return result
}

const getRow = (widgets, i) => (
    <GridRow key={i}>
        {widgets.map(widget => <GridColumn><Container>{widget}</Container></GridColumn>)}
    </GridRow>
)

export const WidgetPane = props => {

    const {
        children,
        cols = 1
    } = props

    const childrenByRows = reshape(React.Children.toArray(children), cols)

    return (
        <Grid columns={cols}>
            {childrenByRows.map(getRow)}
        </Grid>
    )
}