package com.example.restservice.service.calendarcostmap;

import com.example.restservice.dto.calendarcostmap.CalendarCostMapDTO;
import com.example.restservice.dto.calendarcostmap.CalendarCostMapFilterDTO;
import com.example.restservice.entity.calendarcostmap.CalendarCostMapEntity;
import com.example.restservice.mapper.CalendarCostMapMapper;
import com.example.restservice.repository.calendarcostmap.CalendarCostMapRepository;
import com.example.restservice.service.ALTOResourceGenericRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalendarCostMapServiceImpl extends ALTOResourceGenericRepoService<CalendarCostMapEntity,
                                                                               CalendarCostMapDTO,
                                                                               CalendarCostMapRepository,
                                                                               CalendarCostMapMapper>
                                     implements CalendarCostMapService {

    @Autowired
    public CalendarCostMapServiceImpl(CalendarCostMapRepository calendarCostMapRepository,
                                      CalendarCostMapMapper calendarCostMapResourceMapper) {
        super(calendarCostMapRepository, calendarCostMapResourceMapper);
    }

    @Override
    public Optional<CalendarCostMapDTO> getResourceWithFilter(String resourceId, String resourceVersion, CalendarCostMapFilterDTO filter) {
        return Optional.empty();
    }

    @Override
    public Optional<CalendarCostMapDTO> getLatestResourceWithFilter(String resourceId, CalendarCostMapFilterDTO filter) {
        return Optional.empty();
    }
}
