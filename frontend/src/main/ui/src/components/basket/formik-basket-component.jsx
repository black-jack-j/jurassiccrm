import React from "react";
import {Input, Select} from "formik-semantic-ui-react";
import './formik-basket.css'
import {Grid, GridColumn} from "semantic-ui-react";

export const FormikBasketComponent = ({fieldName,item}) => {

    const itemFieldName = `${fieldName}.item`
    const countFieldName = `${fieldName}.count`

    return (
        <>
            <Grid>
                <GridColumn width={8}>
                    <Select name={itemFieldName} {...item}/>
                </GridColumn>
                <GridColumn width={4}>

                </GridColumn>
                <GridColumn width={4}>
                    <Input name={countFieldName} type={'number'} className={'countField'} min={1}/>
                </GridColumn>
            </Grid>
        </>
    )

}