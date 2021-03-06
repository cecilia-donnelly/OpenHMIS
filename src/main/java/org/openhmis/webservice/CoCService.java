



package org.openhmis.webservice;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.openhmis.dto.CoCDTO;
import org.openhmis.dto.InventoryDTO;
import org.openhmis.dto.SiteDTO;
import org.openhmis.exception.AccessDeniedException;
import org.openhmis.manager.CoCManager;
import org.openhmis.manager.InventoryManager;
import org.openhmis.manager.SiteManager;
import org.openhmis.util.Authentication;
import org.openhmis.util.DateParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Path("/cocs")
public class CoCService {
	private static final Logger log = Logger.getLogger(CoCService.class);
	public CoCService() {}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<CoCDTO> getCoCs(@HeaderParam("Authorization") String authorization, @QueryParam("updatedSince") String updatedSince) throws JsonProcessingException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.READ))
                        throw new AccessDeniedException();

                List<CoCDTO> coCDTOs;
		// If the user specified no updatedSince parameter, return everything
		if(updatedSince == null) {
			coCDTOs = CoCManager.getCoCs();
		} else {
			coCDTOs = CoCManager.getCoCs(DateParser.parseDate(updatedSince));
		}
                log.info("GET /cocs (" + coCDTOs.size() + " results)");
                return coCDTOs;
	}
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CoCDTO createCoC(@HeaderParam("Authorization") String authorization, CoCDTO inputDTO) throws JsonParseException, JsonMappingException, IOException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.WRITE))
                        throw new AccessDeniedException();
		CoCDTO outputDTO = CoCManager.addCoC(inputDTO);
                log.info("POST /cocs (" + outputDTO.getId() + " results)");
		return outputDTO;
	}
	
	@GET
	@Path("/{coCId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CoCDTO getCoC(@HeaderParam("Authorization") String authorization, @PathParam("coCId") String coCId) throws JsonProcessingException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.READ))
                        throw new AccessDeniedException();
		CoCDTO outputDTO = CoCManager.getCoCByProjectCoCId(coCId);
                log.info("GET /cocs/" + coCId);
		return outputDTO;
	}
	
	@PUT
	@Path("/{coCId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CoCDTO updateCoC(@HeaderParam("Authorization") String authorization, @PathParam("coCId") String coCId, CoCDTO inputDTO) throws JsonParseException, JsonMappingException, IOException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.WRITE))
                        throw new AccessDeniedException();
		inputDTO.setProjectCoCId(coCId);
		
		CoCDTO outputDTO = CoCManager.updateCoC(inputDTO);
                log.info("PUT /cocs/" + coCId);
		return outputDTO;
	}
	
	@DELETE
	@Path("/{coCId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String deleteCoC(@HeaderParam("Authorization") String authorization, @PathParam("coCId") String coCId) throws JsonParseException, JsonMappingException, IOException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.WRITE))
                        throw new AccessDeniedException();
		CoCManager.deleteCoC(coCId);
                log.info("DELETE /cocs/" + coCId);
		return "true";
	}

	/* Inventory Endpoints */
	@GET
	@Path("/{coCId}/inventories")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<InventoryDTO> getInventories(@HeaderParam("Authorization") String authorization, @PathParam("coCId") String coCId, @QueryParam("updatedSince") String updatedSince) throws JsonProcessingException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.READ))
                        throw new AccessDeniedException();

                List<InventoryDTO> inventoryDTOs;
		// If the user specified no updatedSince parameter, return everything
		if(updatedSince == null) {
			inventoryDTOs = InventoryManager.getInventoriesByProjectCoCId(coCId);
		} else {
			inventoryDTOs = InventoryManager.getInventoriesByProjectCoCId(coCId, DateParser.parseDate(updatedSince));
		}
                log.info("GET /" + coCId + "/inventories (" + inventoryDTOs.size() + " results)");
                return inventoryDTOs;
	}

	/* Site Endpoints */
	@GET
	@Path("/{coCId}/sites")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<SiteDTO> getSites(@HeaderParam("Authorization") String authorization, @PathParam("coCId") String coCId, @QueryParam("updatedSince") String updatedSince) throws JsonProcessingException {
		if(!Authentication.googleAuthenticate(authorization, Authentication.READ))
                        throw new AccessDeniedException();

                List<SiteDTO> siteDTOs;
		// If the user specified no updatedSince parameter, return everything
		if(updatedSince == null) {
			siteDTOs = SiteManager.getSitesByProjectCoCId(coCId);
		} else {
			siteDTOs = SiteManager.getSitesByProjectCoCId(coCId, DateParser.parseDate(updatedSince));
		}
                log.info("GET /" + coCId + "/sites (" + siteDTOs.size() + " results)");
                return siteDTOs;
	}

}
