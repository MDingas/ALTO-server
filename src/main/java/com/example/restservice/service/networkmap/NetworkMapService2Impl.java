package com.example.restservice.service.networkmap;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.entity.NetworkMapEntity;
import com.example.restservice.mapper.NetworkMapMapperImpl;
import com.example.restservice.repository.networkmap.NetworkMapRepository;
import com.example.restservice.service.ALTOResourceBaseGenericRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Component
public class NetworkMapService2Impl extends ALTOResourceBaseGenericRepoService<NetworkMapEntity, NetworkMapDTO> implements NetworkMapService {

    private NetworkMapRepository networkMapRepository;

    @Autowired
    public NetworkMapService2Impl(NetworkMapRepository networkMapRepository, NetworkMapMapperImpl networkMapMapper) {
        super(networkMapRepository, networkMapMapper);
        this.networkMapRepository = networkMapRepository;
    }

    @Override
    public List<NetworkMapDTO> getAllNetworkMaps() {
        return this.getAll();
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version) throws NotFoundException {
        return null;
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId) throws NotFoundException {
        return null;
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        return null;
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        System.out.println("Aqui");
        return null;
    }
}
