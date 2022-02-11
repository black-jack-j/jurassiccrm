import _ from "lodash";

const isStartsWithDog = value => {
    const stringVal = value.toString()
    return stringVal.length > 0 && stringVal.indexOf('@') === 0
}

const searchByNamePredicateBuilder = re => ({firstName, lastName}) => re.test(firstName) || re.test(lastName)
const searchByUsernamePredicateBuilder = re => ({username}) => re.test(username)

export const searchByNameOrUsernameBuilder = value => {

    const re = isStartsWithDog(value) ?
        new RegExp(_.escapeRegExp(value.substring(1)), 'i') :
        new RegExp(_.escapeRegExp(value), 'i')

    const predicate = isStartsWithDog(value) ?
        searchByUsernamePredicateBuilder(re) :
        searchByNamePredicateBuilder(re)

    return users => users.filter(predicate)
}