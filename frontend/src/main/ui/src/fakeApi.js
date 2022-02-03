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
        ]
    },
    research: {
        getAllResearches: async () => [
            {id: 1, name: 'Awesome research'},
            {id: 2, name: 'Terrible Research'}
        ]
    }
}