package uszipcodes

class Zip {

    String number

    static belongsTo = [state: State]

    static constraints = {
    }
}
