package uszipcodes



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TagCloudController)
@Mock(State)
class TagCloudControllerTests {

    void testIndex() {
        controller.index()

        // Fails because tags not recognized.
        assert tags.count() == 50
    }
}
