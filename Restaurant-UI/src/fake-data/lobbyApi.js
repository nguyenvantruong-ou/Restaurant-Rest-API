import Lobbys from './lobby'

const getAll = () => {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(Lobbys)
        }, 250)
    })
}

export default getAll;