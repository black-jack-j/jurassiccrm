import {UserRolesEnum} from "./generatedclient/models";

export const fakeAPI = {
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
    aviaryType: {
        getAllAviaries: async () =>
            [
                {id: 23, name: 'Большой открытый'},
                {id: 56, name: 'Клетка XXL'}
            ]
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
        ]
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
    }
}