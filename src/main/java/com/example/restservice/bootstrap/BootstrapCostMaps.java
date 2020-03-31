package com.example.restservice.bootstrap;

import com.example.restservice.dto.costmap.*;
import com.example.restservice.repository.CostMapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.*;

//@Component
public class BootstrapCostMaps implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BootstrapNetworkMaps.class);

    private CostMapRepository costMapRepository;

    @Autowired
    public BootstrapCostMaps(CostMapRepository costMapRepository) {
        this.costMapRepository = costMapRepository;
    }

    private static CostTypeDTO generateCostTypeDTO(int seed) {
        CostTypeDTO costTypeDTO;

        if (seed % 2 == 0) {
            String description = "description" + seed;
            costTypeDTO = new CostTypeDTO(CostModeDTO.NUMERICAL, CostMetricDTO.HOP_COUNT, description);
        } else {
            costTypeDTO = new CostTypeDTO(CostModeDTO.NUMERICAL, CostMetricDTO.HOP_COUNT, null);
        }

        return costTypeDTO;
    }

    private static CostMapDTO generateCostMapDTO(int seed) {
        Random random = new Random(seed);
        String resourceId = "resource" + seed;
        String tag = "tag" + seed;
        CostTypeDTO costTypeDTO = generateCostTypeDTO(random.nextInt(10));

        MetaDataDTO metaDataDTO = new MetaDataDTO(new VersionTagDTO(resourceId, tag), null, costTypeDTO);


        Map<String, Integer> costMapping = new HashMap<>();
        costMapping.put("pid2", 10);
        Map<String, Map<String, Integer>> costMappings = new HashMap<>();
        costMappings.put("pid1", costMapping);

        return new CostMapDTO(metaDataDTO, costMappings);
    }

    private static List<CostMapDTO> generateCostMapDTOs(int numberToGenerate) {
        List<CostMapDTO> generatedCostMapDTOs = new ArrayList<>();

        for (int i = 0; i < numberToGenerate; i++) {
            generatedCostMapDTOs.add(generateCostMapDTO(i));
        }

        return generatedCostMapDTOs;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing bootstrapping of cost maps");

        List<CostMapDTO> generatedCostMapDTOs = generateCostMapDTOs(10);
        costMapRepository.saveAll(generatedCostMapDTOs);

        logger.info("Finished bootstrapping of cost maps");
    }
}
