package com.example.restservice.bootstrap;

import com.example.restservice.dto.*;
import com.example.restservice.repository.NetworkMapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootstrapNetworkMaps implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapNetworkMaps.class);

    private NetworkMapRepository networkMapRepository;

    @Autowired
    public BootstrapNetworkMaps(NetworkMapRepository networkMapRepository) {
        this.networkMapRepository = networkMapRepository;
    }

    private static NetworkMapDTO generateNetworkMapDTO(int seed) {
        String resourceId = "resource" + seed;
        String tag = "tag" + seed;
        MetaDataDTO metaDataDTO = new MetaDataDTO(new VersionTagDTO(resourceId, tag));

        String[] ipv4Addresses = {"10.0.0." + seed, "192.168.1." + seed, "232.20.31." + seed};
        String[] ipv6Addresses = null;
        IpAggregationsDTO ipAggregationsDTO = new IpAggregationsDTO(ipv4Addresses, ipv6Addresses);
        Map<String, IpAggregationsDTO> ipAggregations = new HashMap<>();
        ipAggregations.put("pid" + seed, ipAggregationsDTO);

        return new NetworkMapDTO(metaDataDTO, ipAggregations);
    }

    private static List<NetworkMapDTO> generateNetworkMapDTOs(int numberToGenerate) {
        List<NetworkMapDTO> generatedNetworkMapDTOs = new ArrayList<>();

        for (int i = 0; i < numberToGenerate; i++) {
            generatedNetworkMapDTOs.add(generateNetworkMapDTO(i));
        }

        return generatedNetworkMapDTOs;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing bootstrapping of network maps");

        List<NetworkMapDTO> generatedNetworkMapDTOs = generateNetworkMapDTOs(10);
        networkMapRepository.saveAll(generatedNetworkMapDTOs);

        logger.info("Finished bootstrapping of network maps");
    }
}
