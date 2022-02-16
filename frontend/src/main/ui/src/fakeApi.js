import {FindAllByRolesAnyRolesEnum as UserRolesEnum} from "./generatedclient/apis";
import {LocalDate} from "js-joda";
import _ from "lodash";
import {GroupOutputTORolesEnum as RoleEnum} from './generatedclient/index'

const baseDate = LocalDate.now()
const template = "Aviary #"

const getIthRevision = i => ({revisionDate: baseDate.plusDays(i), aviary: {id: i, name: `${template} ${i++}`}})

export const fakeAPI = {
    task: {
        getPriorities: async () => [
            {id: 1, name: 'normal'},
            {id: 2, name: 'urgent'},
            {id: 3, name: 'ASAP'}
        ],
        createTask: async value => value
    },
    dinosaur: {
        getAllDinosaurTypes: async () =>
            [
                {id: 10, name: 'Трицератопс'},
                {id: 42, name: 'Тираннозавр'}
            ],
        getAllDinosaurStatuses: async () => [
            'Родился',
            'Пригодился',
            'Умер'
        ]
    },
    aviary: {
        getAllAviaryTypes: async () => (
            [
                {id: 23, name: 'Большой открытый'},
                {id: 56, name: 'Клетка XXL'}
            ]
        ),
        getNextAviaryRevisions: async () =>  _.range(1, 10).map(getIthRevision)
    },
    decorationType: {
        getAllDecorations: async () => [
            {id: 43, name: 'Куст №37'},
            {id: 25, name: 'Дерево №29'}
        ]
    },
    user: {
        findAllByRolesAny: async () => [
            {id: 1, username: 'ATest', firstName: 'BName', lastName: 'CLastName'},
            {id: 2, username: 'BTest', firstName: 'AName', lastName: 'DLastName'},
            {id: 3, username: 'ETest', firstName: 'DName', lastName: 'FLastName'}
        ],
        getCurrentUserRoles: async () => [
            UserRolesEnum.Admin
        ],
        getUsers: async () => [
            {id: 1, username: 'ATest', firstName: 'BName', lastName: 'CLastName'},
            {id: 2, username: 'BTest', firstName: 'AName', lastName: 'DLastName'},
            {id: 3, username: 'ETest', firstName: 'DName', lastName: 'FLastName'}
        ],
        createUser: async ({avatar, userInfo}) => {
            console.log(userInfo)
        }
    },
    research: {
        getAllResearches: async () => [
            {id: 1, name: 'Awesome research'},
            {id: 2, name: 'Terrible Research'}
        ]
    },
    document: {
        getAllDocuments: async () => [
            {id: 1, name: 'Doc 1', type: 'DINOSAUR_PASSPORT'},
            {id: 2, name: 'Doc 2', type: 'THEME_ZONE_PROJECT'}
        ]
    },
    log: {
        getLogs: async () => [
            {username: 'ATest', action: 'Add dinosaur passport', timestamp: new Date()},
            {username: 'BTest', action: 'Add aviary passport', timestamp: new Date()},
            {username: 'ETest', action: 'Add technological map', timestamp: new Date()},
            {username: 'ATest', action: 'Add dinosaur passport', timestamp: new Date()},
            {username: 'BTest', action: 'Add aviary passport', timestamp: new Date()},
            {
                username: 'BTest',
                action: 'Some long and boring action which can possibly take all the space so it should be truncated',
                timestamp: new Date()
            },
        ]
    },
    role: {
        getAllRoles: async () => Object.values(RoleEnum)
    },
    group: {
        createGroup: async group => {
            console.log("MAKING FAKE REQUEST. Payload: "+JSON.stringify(group))
        },
        getGroup: async groupId => {
            if (groupId) {
                return {id: groupId, name: 'Mock group', description: 'Some description', users: [], roles: []}
            } else {
                throw new Error()
            }
        },
        getGroupIcon: async groupId => {
            return "data:image/jpeg;base64,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"
        },
        getAllGroupsSimple: async () => [
            {id: 1, name: 'Test group 1'},
            {id: 2, name: 'Test group 2'},
            {id: 3, name: 'Test group 3'}
        ]
    }
}