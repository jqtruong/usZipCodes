package uszipcodes

import grails.converters.JSON

// Some logic graciously taken from http://www.ibm.com/developerworks/java/library/j-grails05208/index.html.
class GeonamesService {
    def grailsApplication

    /**
     * Calls the Geonames API to get all of the states.
     */
    def getUsStates() {
        def results = getResults(new URL(grailsApplication.config.geonames.urls.us_children), false)

        // Create State objects from the results.
        for (geoname in results.geonames) {
            // Filter out Washington D.C. by the fact that it is not a state, thus doesn't have any children.
            if (!geoname.numberOfChildren) continue
            def state = new State(code:geoname.adminCode1, name:geoname.name)

            // Call the Geonames API again to retrieve each state's postal codes, but only the xml's total results is used for the tag cloud.
            def xml = getResults(new URL(grailsApplication.config.geonames.urls.state_postalCodes + URLEncoder.encode(state.code + " " + state.name)), true)
            state.numberOfPostalCodes = xml.totalResultsCount.toInteger()
            
            state.save()
        }
    }

    /**
     * Class helper to retrieve the results from the Geonames API
     *
     * @param URL     url The service url.
     * @param boolean xml Determines type of results returned.
     * 
     * @return XML or JSON object based on the xml param.
     */
    def getResults(URL url, boolean xml) {
        def connection = url.openConnection()
        
        def results
        // Either set results to XML or JSON based on xml parameter.
        if(connection.responseCode == 200){
            if (xml) {
                results = new XmlSlurper().parseText(connection.content.text)
            }
            else {
                results = JSON.parse(connection.content.text)
            }
        }
        // Else log the error.
        else{
            log.error("GeonamesService.getResults FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }

        return results
    }

}
