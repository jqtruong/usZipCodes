package uszipcodes



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GeonamesService)
@Mock(State)
class GeonamesServiceTests {

    void testGetUsStates() {
        // Mock domain class.

        service.getUsStates()

        assert State.count() == 50
    }
}
