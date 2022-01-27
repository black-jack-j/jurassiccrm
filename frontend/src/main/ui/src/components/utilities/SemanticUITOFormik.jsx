import React from "react"
import {Dropdown, FormField} from "semantic-ui-react"
import {useField} from "formik";

export const SemanticFormikSelectInputField = ({name, options, placeholder, ...props}) => {

    const [value, meta, handlers] = useField(name)

    const handleInput = (event, {value}) => handlers.setValue(value)

    return (
        <FormField>
            <Dropdown options={options}
                      fluid
                      search
                      selection
                      placeholder={placeholder}
                      onChange={handleInput}/>
        </FormField>
    );
}