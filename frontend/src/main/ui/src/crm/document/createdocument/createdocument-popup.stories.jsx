import {CreateDocumentPopup} from "./create-document-popup";
import {Provider, useDispatch} from "react-redux";
import store from "../../store/store";
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

export default {
    title: 'Create Document Popup',
    components: [CreateDocumentPopup],
}

const PopupWithButton = ({type, ...props}) => {

    const dispatch = useDispatch();

    return (
        <>
            <Button onClick={() => dispatch(createDocument(type))}>
                {`Create ${type}`}
            </Button>
            <CreateDocumentPopup />
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
    type: DINOSAUR_PASSPORT
}

export const CreateAviaryPassport = Template.bind({})
CreateAviaryPassport.args = {
    type: AVIARY_PASSPORT
}

export const CreateResearchMaterial = Template.bind({})
CreateResearchMaterial.args = {
    type: RESEARCH_MATERIAL
}

export const CreateTechMap = Template.bind({})
CreateTechMap.args = {
    type: TECHNOLOGICAL_MAP
}

export const CreateThemeZoneProject = Template.bind({})
CreateThemeZoneProject.args = {
    type: THEME_ZONE_PROJECT
}