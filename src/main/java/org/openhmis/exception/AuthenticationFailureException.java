package org.openhmis.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.openhmis.dto.error.AuthenticationFailureErrorDTO;
import org.openhmis.dto.error.RecordNotFoundErrorDTO;
import org.openhmis.dto.error.RootErrorDTO;

public class AuthenticationFailureException extends WebApplicationException {
     /**
      * Create a HTTP 404 (Not Found) exception.
      */
     public AuthenticationFailureException() {
         super(Response
        		 .status(Response.Status.NOT_FOUND)
        		 .entity(new RootErrorDTO(new AuthenticationFailureErrorDTO()))
        		 .build());
     }
  
 }