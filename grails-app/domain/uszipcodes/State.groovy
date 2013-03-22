package uszipcodes

class State implements Comparable {

    // E.g. MN.
    String code

    // E.g. Minnesota.
    String name

    // Total number of zip/postal code for this state.
    Number numberOfPostalCodes

    static constraints = {
    }

    static mapping = {
        sort "name"
    }

    // Used to order States alphabetically based on the State's name.
    int compareTo(obj) {
        name.compareTo(obj.name)
    }
}
