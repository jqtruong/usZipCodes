package uszipcodes

import grails.converters.JSON

class TagCloudController {
    def geonamesService

    Map tags
    String stateInfo

    /**
     * Displays the tag cloud.
     */
    def index() {
        // Retrieve all the State objects sorted by name.
        def states = State.list(sort: "name")

        // Create a tags map with State's name as key and number of codes as value.
        tags = [:]
        for (state in states) {
            tags.(state.name) = state.numberOfPostalCodes
        }

        // Just to display some extra info is a link in the cloud was clicked,
        // this bit of code will show how many postal codes does the selected State has.
        stateInfo = ""
        if (params.id) {
            def state = State.findByName(params.id)
            stateInfo = state.name + " contains " + state.numberOfPostalCodes + " postal codes."
        }
    }

    /**
     * Delete all the State objects and replace the tag cloud with some text.
     */
    def clear() {
        State.executeUpdate('delete from State')
        tags = [:]
        render("No data.")
    }

    /**
     * Retrieve all of the States postal codes and reload page (done through javascript).
     */
    def recollect() {
        geonamesService.getUsStates()
        render("Reloading page...")
    }
}
