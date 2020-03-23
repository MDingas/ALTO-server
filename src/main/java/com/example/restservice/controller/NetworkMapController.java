package com.example.restservice.controller;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("networkmaps")
public class NetworkMapController {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMapController.class);

    private Service service;

    @Autowired
    public NetworkMapController(Service service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<NetworkMapDTO> getNetworkMaps() {
        logger.info("Request for retrieval of all network maps");
        return service.getAllNetworkMaps();
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void postNetworkMap(@Valid @RequestBody NetworkMapDTO networkMapDTO) {
        logger.info("Request for insertion of new network map");
        service.putNetworkMapDTO(networkMapDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{resourceId}")
    public NetworkMapDTO getNetworkMap(@PathVariable(value = "resourceId") String resourceId,
                                       @QueryParam(value = "version") String version) {
        logger.info(String.format("Request for retrieval a single network map (resource_id=%s, version=%s)", resourceId, version));

        NetworkMapDTO networkMapDTO;

        try {
            if (version != null) {
                networkMapDTO = service.getNetworkMap(resourceId, version);
            } else {
                networkMapDTO = service.getLatestNetworkMap(resourceId);
            }
        } catch (NotFoundException e) {
            logger.warn(String.format("Could not find network map (resource_id=%s, version=%s)", resourceId, version));
            throw new NotFoundException("Could not find network map");
        }
        return networkMapDTO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "{resourceId}")
    public NetworkMapDTO getNetworkMapWithFilter(@PathVariable(value = "resourceId") String resourceId,
                                                 @QueryParam(value = "version") String version,
                                                 @Valid @RequestBody NetworkMapFilterDTO networkMapFilterDTO) {
        logger.info(String.format("Request for retrieval of a single network map (resource_id=%s, version=%s, pids=%s", resourceId, version, networkMapFilterDTO.getsrcPIDs().toString()));
        NetworkMapDTO networkMapDTO;

        try {
            if (version != null) {
                networkMapDTO = service.getNetworkMap(resourceId, version, networkMapFilterDTO);
            }
            else {
                //networkMapDTO = service.getLatestFilteredNetworkMap(resourceId, networkMapFilterDTO);
                networkMapDTO = null;
            }
        } catch (NotFoundException e) {
            logger.warn(String.format("Could not find network map (resource_id=%s, version=%s)", resourceId, version));
            throw new NotFoundException("Could not find network map");
        }
        return networkMapDTO;
    }
}
