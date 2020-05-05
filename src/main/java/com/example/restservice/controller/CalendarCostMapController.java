package com.example.restservice.controller;

import com.example.restservice.dto.calendarcostmap.CalendarCostMapDTO;
import com.example.restservice.dto.calendarcostmap.CalendarCostMapFilterDTO;
import com.example.restservice.service.calendarcostmap.CalendarCostMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calendarcostmaps")
public class CalendarCostMapController extends ALTOResourceCRUDController<CalendarCostMapDTO, CalendarCostMapFilterDTO, CalendarCostMapService> {

    @Autowired
    public CalendarCostMapController(CalendarCostMapService calendarCostMapService) {
        super(calendarCostMapService, "Calendar Cost Map");
    }
}
