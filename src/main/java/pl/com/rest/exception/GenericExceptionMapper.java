package pl.com.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wewe on 29.06.16.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable ex) {

        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setCode(999);
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        errorMessage.setLink(null);

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
        if(ex instanceof WebApplicationException) {
            errorMessage.setStatus(((WebApplicationException)ex).getResponse().getStatus());

        } else {
            errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); //defaults to internal server error 500
        }
    }
}
