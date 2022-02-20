import React from "react";
import {Input, Select} from "formik-semantic-ui-react";
import './formik-basket.css'
import {Grid, GridColumn} from "semantic-ui-react";
import {FormikSelect} from "../formik-select/formik-select";

export const FormikBasketComponent = ({fieldName,item}) => {

    const itemFieldName = `${fieldName}.item`
    const countFieldName = `${fieldName}.count`

    return (
        <>
            <Grid>
                <GridColumn width={10}>
                    <FormikSelect style={{width: 250}} name={itemFieldName} {...item}/>
                </GridColumn>
                <GridColumn width={2}>

                </GridColumn>
                <GridColumn width={4}>
                    <Input style={{width: 60}} name={countFieldName} type={'number'} className={'countField'} min={1}/>
                </GridColumn>
            </Grid>
        </>
    )

}