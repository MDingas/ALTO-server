package com.example.restservice.controller;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.service.networkmap.NetworkMapService;
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

    private NetworkMapService networkMapService;

    @Autowired
    public NetworkMapController(NetworkMapService service) {
        this.networkMapService = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<NetworkMapDTO> getNetworkMaps() {
        return networkMapService.getAllNetworkMaps();
    }

    //@RequestMapping(method = RequestMethod.POST, value = "")
    //public void postNetworkMap(@Valid @RequestBody NetworkMapDTO networkMapDTO) {
    //    logger.info("Request for insertion of new network map");
    //    networkMapService.putNetworkMapDTO(networkMapDTO);
    //}

    @RequestMapping(method = RequestMethod.GET, value = "{resourceId}")
    public NetworkMapDTO getNetworkMap(@PathVariable(value = "resourceId") String resourceId,
                                       @QueryParam(value = "version") String version) {
        if (version != null) {
            try {
                return networkMapService.getNetworkMap(resourceId, version);
            } catch (NotFoundException e) {
                throw new NotFoundException("Could not find network map with this version");
            }
        } else {
            try {
                return networkMapService.getLatestNetworkMap(resourceId);
            } catch (NotFoundException e) {
                throw new NotFoundException("Network map does not have any versions");
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{resourceId}")
    public NetworkMapDTO getNetworkMapWithFilter(@PathVariable(value = "resourceId") String resourceId,
                                                 @QueryParam(value = "version") String version,
                                                 @Valid @RequestBody NetworkMapFilterDTO networkMapFilterDTO) {
        NetworkMapDTO networkMapDTO;

        if (version != null) {
            try {
                networkMapDTO = networkMapService.getNetworkMap(resourceId, version, networkMapFilterDTO);
            } catch (NotFoundException e) {
                throw new NotFoundException("Could not find network map with given filter");
            }
        }
        else {
            try {
                networkMapDTO = networkMapService.getLatestNetworkMap(resourceId, networkMapFilterDTO);
            } catch (NotFoundException e) {
                throw new NotFoundException("Could not find latest version of network map with given filter");
            }
        }
        return networkMapDTO;
    }
}
