package com.example.restservice.bootstrap;

import com.example.restservice.dto.networkmap.IpAggregationsDTO;
import com.example.restservice.dto.networkmap.MetaDataDTO;
import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.VersionTagDTO;
import com.example.restservice.repository.NetworkMapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
public class BootstrapNetworkMaps implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapNetworkMaps.class);

    private NetworkMapRepository networkMapRepository;

    @Autowired
    public BootstrapNetworkMaps(NetworkMapRepository networkMapRepository) {
        this.networkMapRepository = networkMapRepository;
    }

    private static NetworkMapDTO generateNetworkMapDTO(int seed) {
        Random random = new Random(seed);
        String resourceId = "resource" + seed;
        String tag = "tag" + seed;
        MetaDataDTO metaDataDTO = new MetaDataDTO(new VersionTagDTO(resourceId, tag));

        String[] ipv4Addresses = new String[4];
        ipv4Addresses[0] = String.format("%s.%s.%s.%s/24", random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        ipv4Addresses[1] = String.format("%s.%s.%s.%s/24", random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        ipv4Addresses[2] = String.format("%s.%s.%s.%s/24", random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        ipv4Addresses[3] = String.format("%s.%s.%s.%s/24", random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));

        String[] ipv6Addresses = new String[1];
        ipv6Addresses[0] = "::/0";

        IpAggregationsDTO ipAggregationsDTO = new IpAggregationsDTO(ipv4Addresses, ipv6Addresses);

        Map<String, IpAggregationsDTO> ipAggregations = new HashMap<>();
        for (int i = 0; i < random.nextInt(5); i++) {
            ipAggregations.put("pid" + i, ipAggregationsDTO);
        }

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
