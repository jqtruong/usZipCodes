import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

    def appCtx 

    def init = { servletContext ->
        // Get the application context to be able to call services from bootrap.
        appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
        // Initialize state objects on bootstrap to display a non-empty tag cloud by default.
        appCtx.geonamesService.getUsStates()
    }

    def destroy = {
    }
}
