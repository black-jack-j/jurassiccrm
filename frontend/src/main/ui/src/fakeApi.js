import {FindAllByRolesAnyRolesEnum as UserRolesEnum} from "./generatedclient/apis";
import {ChronoUnit, Instant} from "js-joda";
import _ from "lodash";
import {GroupOutputTORolesEnum as RoleEnum} from './generatedclient/index'

const baseDate = Instant.now()
const template = "Aviary #"

const getIthRevision = i => ({revisionDate: baseDate.plus(10*i, ChronoUnit.DAYS ).toEpochMilli(), aviary: {id: i, name: `${template} ${i++}`}})

const fakeAviaryPassport = {
    name: 'Test Document',
    created: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
    lastUpdated: Instant.now().minus(5, ChronoUnit.DAYS).toEpochMilli(),
    description: 'Some description',
    documentType: 'AVIARY_PASSPORT',
    code: '001C',
    revisionPeriod: 2,
    builtDate: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
    status: 'Normal',
    aviaryType: {
        id: 1,
        name: 'Test type'
    },
    square: 10
}

const fakeDinosaurPassport = {
    name: 'Test Dino Pass',
    created: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
    lastUpdated: Instant.now().minus(5, ChronoUnit.DAYS).toEpochMilli(),
    description: 'Some description',
    documentType: 'DINOSAUR_PASSPORT',
    dinosaurType: {
        id: 1,
        name: 'Test dino type'
    },
    dinosaurName: 'Grumpy',
    weight: 2,
    height: 2,
    status: 'OK',
    revisionPeriod: 1,
    incubated: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
}

const fakeTechnologicalMap = {

    name: 'Test Technological Map',
    created: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
    lastUpdated: Instant.now().minus(5, ChronoUnit.DAYS).toEpochMilli(),
    documentType: 'TECHNOLOGICAL_MAP',
    dinosaurType: {
        id: 1,
        name: 'Test dino type'
    },
    incubationSteps: [
        'first step',
        'seconds step'
    ],
    eggCreationSteps: [
        'first step',
        'second step'
    ]

}

const fakeResearchData = async() => {

    const url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScrwpDOWx6lUIZ3aKDgJJiTLT5apLiN5iN5w&usqp=CAU"

    const result = await fetch(url)
    const blob = await result.blob()

    return ({
        name: 'Test Dino Pass',
        created: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
        lastUpdated: Instant.now().minus(5, ChronoUnit.DAYS).toEpochMilli(),
        description: 'Some description',
        documentType: 'RESEARCH_DATA',
        attachmentName: 'My attachment',
        research: {
            id: 2,
            name: 'My awesome research'
        },
        attachment: blob
    })

}

const fakeThemeZoneProject = ({
    name: 'Test Theme Zone Project',
    created: Instant.now().minus(10, ChronoUnit.DAYS).toEpochMilli(),
    lastUpdated: Instant.now().minus(5, ChronoUnit.DAYS).toEpochMilli(),
    documentType: 'THEME_ZONE_PROJECT',
    projectName: 'Test theme zone',
    manager: {
        id: 42,
        username: 'pewdiepie',
        firstName: 'Test name',
        lastName: 'Test lastName'
    },
    dinosaurs: [
        {type: {id: 1, name: 'TRex'}, number: 2},
        {type: {id: 2, name: 'Test'}, number: 3}
    ],
    aviaries: [
        {type: {id: 1, name: 'XXL'}, number: 2},
        {type: {id: 3, name: 'XL'}, number: 1}
    ],
    decorations: [
        {type: {id: 1, name: 'Rock'}, number: 5},
        {type: {id: 2, name: 'Palm tree'}, number: 11}
    ]
})

export const fakeAPI = {
    task: {
        getPriorities: async () => [
            {id: 1, name: 'normal'},
            {id: 2, name: 'urgent'},
            {id: 3, name: 'ASAP'}
        ],
        createTask: async values => {
            console.log(values)
            return {}
        },
        getTasks: async () => [
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            },
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            },
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            },
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            },
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            },
            {
                id: 1,
                name: 'Some task',
                description: 'Some description',
                created: Instant.now().toEpochMilli(),
                lastUpdated: Instant.now().toEpochMilli()
            }
        ]
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
        },
        getUserById: async ({userId}) => ({
            id: userId,
            firstName: 'Test' + userId,
            lastName: 'Testovich',
            username: 'test',
            department: "ADMINISTRATION",
            groupIds: [
                {id: 1, name: 'test_group1', description: 'Some description'},
                {id: 2, name: 'test_group2', description: 'Some description 2'},
                {id: 3, name: 'test_group3', description: 'Some description 3'}
            ]
        }),
        getUsersSimpleRaw: async () => ([
            {id: 1, username: 'ATest', firstName: 'BName', lastName: 'CLastName', department: 'ADMINISTRATION'},
            {id: 2, username: 'BTest', firstName: 'AName', lastName: 'DLastName', department: 'RESEARCH'},
            {id: 3, username: 'ETest', firstName: 'DName', lastName: 'FLastName', department: 'MAINTENANCE'}
        ]),
        getUsersSimple() {
            return this.getUsersSimpleRaw()
        },
        getCurrentUser: async () => ({
            id: 1,
            avatarSrc: 'data:image/jpeg;base64,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',
            username: 'Test',
            firstName: 'Ivan',
            lastName: 'Ivanov',
            department: 'ADMINISTRATION',
            groups: [
                {id: 1, name: 'Administration', description: 'Some description'}
            ],
            roles: [
                RoleEnum.AviaryPassportWriter,
                RoleEnum.ResearchDataWriter,
                RoleEnum.AviaryBuildingTaskWriter,
                RoleEnum.ResearchTaskWriter,
                RoleEnum.SecurityWriter
            ]
        })
    },
    research: {
        getAllResearches: async () => [
            {id: 1, name: 'Awesome research'},
            {id: 2, name: 'Terrible Research'}
        ]
    },
    document: {
        getAllDocuments: async () => [
            {id: 1, name: 'Dino Pass 1', documentType: 'DINOSAUR_PASSPORT'},
            {id: 2, name: 'Project 1', documentType: 'THEME_ZONE_PROJECT'},
            {id: 3, name: 'Aviary Pass 1', documentType: 'AVIARY_PASSPORT'},
            {id: 4, name: 'Research', documentType: 'RESEARCH_DATA'},
            {id: 5, name: 'Tech Map 1', documentType: 'TECHNOLOGICAL_MAP'}
        ],
        getDocumentById: async ({documentType}) => {
            switch(documentType) {
                case 'AVIARY_PASSPORT': return fakeAviaryPassport
                case 'DINOSAUR_PASSPORT': return fakeDinosaurPassport
                case 'RESEARCH_DATA': return fakeResearchData()
                case 'TECHNOLOGICAL_MAP': return fakeTechnologicalMap
                case 'THEME_ZONE_PROJECT': return fakeThemeZoneProject
            }
        },
        updateDocument: async values => {
            console.log(values)
            return {}
        },
        updateResearchData: async values => {
            console.log(values)
            return {}
        }
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
        getAllGroups: async () => [

        ],
        getAllGroupsSimple: async () => [
            {id: 1, name: 'Test group 1'},
            {id: 2, name: 'Test group 2'},
            {id: 3, name: 'Test group 3'}
        ]
    }
}