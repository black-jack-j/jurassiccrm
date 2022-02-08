import React, {useState} from "react";
import {Dropdown, Menu as SemanticMenu, MenuItem, MenuMenu, Segment} from "semantic-ui-react";
import {useTranslation} from "react-i18next";

export const Menu = ({options, active=undefined, defaultLang='en'}) => {
    const activeTab = typeof active === 'undefined' ? options[0].key : active
    const [activeTabKey, setActiveTab] = useState(activeTab)
    const [activeLang, setActiveLang] = useState(defaultLang)
    const {t, i18n} = useTranslation()

    const setLanguage = (lang) => {
        i18n.changeLanguage(lang).then(console.log)
        setActiveLang(lang)
    }

    return (
        <>
            <SemanticMenu attached={'top'} tabular>
                {options.map(({key, text}) => (
                    <MenuItem onClick={() => setActiveTab(key)} active={key === activeTabKey}>
                        {t(text)}
                    </MenuItem>
                ))}
                <MenuMenu position='right'>
                    <MenuItem>
                        <Dropdown inline
                                  className={'icon'}
                                  text={activeLang}
                                  labeled
                                  icon={'world'}
                                  floating
                                  options={
                                      [
                                          {key: 'en', value: 'en', text: 'en'},
                                          {key: 'ru', value: 'ru', text: 'ru'}
                                      ]}
                                  onChange={(e, {value}) => setLanguage(value)}
                        />
                    </MenuItem>
                </MenuMenu>
            </SemanticMenu>
            <Segment attached={'bottom'}>
                {options.find(({key}) => activeTabKey === key).render()}
            </Segment>
        </>

    )
}