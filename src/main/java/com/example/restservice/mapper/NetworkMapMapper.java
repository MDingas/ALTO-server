package com.example.restservice.mapper;

import com.example.restservice.dto.networkmap.IpAggregationsDTO;
import com.example.restservice.dto.networkmap.MetaDataDTO;
import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.VersionTagDTO;
import com.example.restservice.entity.AddressAggregationEntity;
import com.example.restservice.entity.NetworkMapEntity;
import com.example.restservice.entity.NetworkMappingsEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NetworkMapMapper implements ALTOMapper<NetworkMapEntity, NetworkMapDTO> {

    private NetworkMapDTO buildNetworkMapDTO(String resourceId, NetworkMappingsEntity networkMappingsEntity) {
        String versionTag = networkMappingsEntity.getVersionTag();

        List<AddressAggregationEntity> addressAggregationEntityList = networkMappingsEntity.getAddressAggregationEntities();
        Map<String, IpAggregationsDTO> ipAggregationsDTOMap = buildPidToIpAggregationsMap(addressAggregationEntityList);

        VersionTagDTO versionTagDTO = new VersionTagDTO(resourceId, versionTag);
        MetaDataDTO metaDataDTO = new MetaDataDTO(versionTagDTO);

        return new NetworkMapDTO(metaDataDTO, ipAggregationsDTOMap);
    }

    private List<NetworkMapDTO> buildNetworkMapDTOs(String resourceId, List<NetworkMappingsEntity> networkMappingsEntities) {
        List<NetworkMapDTO> networkMapDTOList = new ArrayList<>();

        for (NetworkMappingsEntity networkMappingsEntity : networkMappingsEntities) {
            networkMapDTOList.add(buildNetworkMapDTO(resourceId, networkMappingsEntity));
        }

        return networkMapDTOList;
    }

    private IpAggregationsDTO buildIpAggregationsDTO(AddressAggregationEntity addressAggregationEntity) {
        String[] ipv4AddressList = addressAggregationEntity.getIpv4AddressList().isPresent() ? addressAggregationEntity.getIpv4AddressList().get().toArray(new String[0]) : null;
        String[] ipv6AddressList = addressAggregationEntity.getIpv6AddressList().isPresent() ? addressAggregationEntity.getIpv6AddressList().get().toArray(new String[0]) : null;

        return new IpAggregationsDTO(ipv4AddressList, ipv6AddressList);
    }

    private Map<String, IpAggregationsDTO> buildPidToIpAggregationsMap(List<AddressAggregationEntity> addressAggregationEntityList) {
        Map<String, IpAggregationsDTO> ipAggregationsDTOMap = new HashMap<>();

        for (AddressAggregationEntity addressAggregationEntity : addressAggregationEntityList) {
            String pid = addressAggregationEntity.getPid();
            ipAggregationsDTOMap.put(pid, buildIpAggregationsDTO(addressAggregationEntity));
        }

        return ipAggregationsDTOMap;
    }

    private Optional<NetworkMappingsEntity> findNetworkMappingsEntityVersion(List<NetworkMappingsEntity> networkMappingsEntities, String versionTag) {
        return networkMappingsEntities
                .stream()
                .filter(resource -> resource.getVersionTag().equals(versionTag))
                .findFirst();
    }

    @Override
    public List<NetworkMapDTO> mapAllVersionsFrom(NetworkMapEntity networkMapEntity) {
        List<NetworkMappingsEntity> networkMappingsEntities = networkMapEntity.getNetworkMappingsEntities();

        String resourceId = networkMapEntity.getResourceId();
        String uri = networkMapEntity.getUri();

        return buildNetworkMapDTOs(resourceId, networkMappingsEntities);
    }

    @Override
    public Optional<NetworkMapDTO> mapFirstVersion(NetworkMapEntity networkMapEntity) {
        String resourceId = networkMapEntity.getResourceId();
        String uri = networkMapEntity.getUri();

        List<NetworkMappingsEntity> networkMappingsEntities = networkMapEntity.getNetworkMappingsEntities();

        if (networkMappingsEntities.isEmpty()) {
            return Optional.empty();
        } else {
            NetworkMappingsEntity firstNetworkMappingsEntity = networkMappingsEntities.get(0);
            NetworkMapDTO networkMapDTO = buildNetworkMapDTO(resourceId, firstNetworkMappingsEntity);
            return Optional.of(networkMapDTO);
        }
    }

    //public Optional<NetworkMapDTO> mapFromVersion(NetworkMapEntity networkMapEntity, String version) {
    //    String resourceId = networkMapEntity.getResourceId();
    //    String uri = networkMapEntity.getUri();

    //    List<NetworkMappingsEntity> networkMappingsEntities = networkMapEntity.getNetworkMappingsEntities();

    //    Optional<NetworkMappingsEntity> optionalNetworkMappingsEntity = findNetworkMappingsEntityVersion(networkMappingsEntities, version);

    //    if (!optionalNetworkMappingsEntity.isPresent()) {
    //        return Optional.empty();
    //    } else {
    //        NetworkMappingsEntity networkMappingsEntity = optionalNetworkMappingsEntity.get();
    //        NetworkMapDTO networkMapDTO = buildNetworkMapDTO(resourceId, networkMappingsEntity);
    //        return Optional.of(networkMapDTO);
    //    }
    //}
}
