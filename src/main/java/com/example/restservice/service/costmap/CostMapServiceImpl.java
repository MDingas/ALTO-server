package com.example.restservice.service.costmap;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.dto.costmap.CostTypeDTO;
import com.example.restservice.dto.costmap.SrcDstMappingsDTO;
import com.example.restservice.entity.CostMapEntity;
import com.example.restservice.mapper.CostMapMapper;
import com.example.restservice.repository.costmap.CostMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CostMapServiceImpl implements CostMapService {

    private CostMapRepository costMapRepository;
    private CostMapMapper costMapMapper;

    @Autowired
    public CostMapServiceImpl(CostMapRepository costMapRepository, CostMapMapper costMapMapper) {
        this.costMapRepository = costMapRepository;
        this.costMapMapper = costMapMapper;
    }

    @Override
    public List<CostMapDTO> getAllCostMaps() {
        List<CostMapDTO> allCostMapDTOList = new ArrayList<>();

        List<CostMapEntity> costMapEntityList = costMapRepository.findAll();

        for (CostMapEntity costMapEntity : costMapEntityList) {
            List<CostMapDTO> versionsOfCostMapDTOList = costMapMapper.mapFromAllVersions(costMapEntity);

            allCostMapDTOList.addAll(versionsOfCostMapDTOList);
        }

        return allCostMapDTOList;
    }

   @Override
   public CostMapDTO getCostMap(String resourceId, String version) throws NotFoundException {
       CostMapEntity costMapEntity = costMapRepository.findVersionOfResource(resourceId, version)
               .orElseThrow(()
                       -> new NotFoundException(String.format("Could not find cost map with id %s and version %s", resourceId, version)));

       return costMapMapper.mapFirstVersion(costMapEntity)
               .orElseThrow(()
               -> new NotFoundException(String.format("Could not find cost map with id %s and version %s", resourceId, version)));
   }

   @Override
   public CostMapDTO getLatestCostMap(String resourceId) throws NotFoundException {
       CostMapEntity costMapEntity = costMapRepository.findLatestVersionOfResource(resourceId)
               .orElseThrow(() ->
                       new NotFoundException(String.format("Could not find any cost map with id %s", resourceId)));

       if (costMapEntity.getCostMappingsEntities().isEmpty()) {
           throw new NotFoundException(String.format("No versions of %s exist", resourceId));
       } else {
           return costMapMapper.mapFirstVersion(costMapEntity)
                   .orElseThrow(()
                   -> new NotFoundException(String.format("Could not find any latest version of %s", resourceId)));
       }
   }

    @Override
    public CostMapDTO getCostMap(String resourceId, String version, CostMapFilterDTO costMapFilterDTO) throws NotFoundException {
        SrcDstMappingsDTO srcDstMappingsDTO = costMapFilterDTO.getSrcDstMappings();
        List<String> srcPIDs = srcDstMappingsDTO.getSrcPIDs();
        List<String> dstPIDs = srcDstMappingsDTO.getDstPIDs();

        CostTypeDTO costTypeDTO = costMapFilterDTO.getCostTypeDTO();
        String costMode = costTypeDTO.getCostMode().getValue();
        String costMetric = costTypeDTO.getCostMetric().getValue();

        CostMapEntity costMapEntity = costMapRepository.findVersionOfResource(resourceId, version, srcPIDs, dstPIDs, costMode, costMetric)
                .orElseThrow(()
                -> new NotFoundException(String.format("Could not find any cost map with resource id %s and version %s", resourceId, version)));

        return costMapMapper.mapFirstVersion(costMapEntity)
                .orElseThrow(()
                -> new NotFoundException(String.format("Could not find cost map with id %s and version %s", resourceId, version)));
    }

   @Override
   public CostMapDTO getLatestCostMap(String resourceId, CostMapFilterDTO costMapFilterDTO) throws NotFoundException {
       SrcDstMappingsDTO srcDstMappingsDTO = costMapFilterDTO.getSrcDstMappings();
       List<String> srcPIDs = srcDstMappingsDTO.getSrcPIDs();
       List<String> dstPIDs = srcDstMappingsDTO.getDstPIDs();

       CostTypeDTO costTypeDTO = costMapFilterDTO.getCostTypeDTO();
       String costMode = costTypeDTO.getCostMode().getValue();
       String costMetric = costTypeDTO.getCostMetric().getValue();

       CostMapEntity costMapEntity = costMapRepository.findLatestVersionOfResource(resourceId, srcPIDs, dstPIDs, costMode, costMetric)
               .orElseThrow(()
               -> new NotFoundException(String.format("Could not find any cost map with id %s", resourceId)));

       return costMapMapper.mapFirstVersion(costMapEntity)
               .orElseThrow(()
                       -> new NotFoundException(String.format("Could not find cost map with id %s", resourceId)));
   }

   // @Override
   // public void putCostMapDTO(CostMapDTO costMapDTO) {
   //     costMapRepository.insert(costMapDTO);
   // }
}
