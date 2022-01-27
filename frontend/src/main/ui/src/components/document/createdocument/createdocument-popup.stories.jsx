import {CreateDocumentPopup} from "./create-document-popup";
import {Provider, useDispatch} from "react-redux";
import store from "../../../store/store";
import React from "react";
import {Button} from "semantic-ui-react";
import {createDocument} from "./create-document-popup-slice";
import {
    AVIARY_PASSPORT,
    DINOSAUR_PASSPORT,
    RESEARCH_MATERIAL,
    TECHNOLOGICAL_MAP,
    THEME_ZONE_PROJECT
} from "./subform/createdocument-subform";

import TechnologicalMapInitialValues from "./subform/technologicalmap/initialValues"
import DinosaurPassportInitialValues from "./subform/dinosaurpassport/initialValues"
import AviaryPassportInitialValues from "./subform/aviarypassport/initialValues"
import ResearchMaterialInitialValues from "./subform/researchmaterial/initialValues"
import ThemeZoneProjectInitialValues from "./subform/themezoneproject/initialValues"
import {ApiProvider} from "../../../api";
import {fakeAPI} from "../../../fakeApi";

export default {
    title: 'Create Document Popup',
    components: [CreateDocumentPopup],
    decorators: [
        Story => {
            return (
                <ApiProvider value={fakeAPI}>
                    <Story/>
                </ApiProvider>
            )
        }
    ]
}

const PopupWithButton = ({type, ...props}) => {

    const dispatch = useDispatch();

    return (
        <>
            <Button onClick={() => dispatch(createDocument(type))}>
                {`Create ${type}`}
            </Button>
            <CreateDocumentPopup {...props}/>
        </>
    )

}

const Template = args => (
    <Provider store={store}>
        <PopupWithButton {...args}/>
    </Provider>
)

export const CreateDinosaurPassport = Template.bind({})
CreateDinosaurPassport.args = {
    type: DINOSAUR_PASSPORT,
    formik: {
        initialValues: {
            ...DinosaurPassportInitialValues
        }
    }
}

export const CreateAviaryPassport = Template.bind({})
CreateAviaryPassport.args = {
    type: AVIARY_PASSPORT,
    formik: {
        initialValues: {
            ...AviaryPassportInitialValues
        }
    }
}

export const CreateResearchMaterial = Template.bind({})
CreateResearchMaterial.args = {
    type: RESEARCH_MATERIAL,
    formik: {
        initialValues: {
            ...ResearchMaterialInitialValues
        }
    }

}

export const CreateTechMap = Template.bind({})
CreateTechMap.args = {
    type: TECHNOLOGICAL_MAP,
    formik: {
        initialValues: {
            ...TechnologicalMapInitialValues
        }
    }
}

export const CreateThemeZoneProject = Template.bind({})
CreateThemeZoneProject.args = {
    type: THEME_ZONE_PROJECT,
    formik: {
        initialValues: {
            ...ThemeZoneProjectInitialValues
        }
    }
}