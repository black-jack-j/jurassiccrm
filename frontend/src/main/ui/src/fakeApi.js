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
    }
}