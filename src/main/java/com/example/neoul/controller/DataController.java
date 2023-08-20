package com.example.neoul.controller;

import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.service.BrandService;
import com.example.neoul.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags={"06.data"})
@RequestMapping("/data")

public class DataController {
    private final DataService dataService;

    @ApiOperation(value = "brand data 수신", notes = "brand data 수신")
    @PostMapping("/saveBrands")
    public void saveBrands(@RequestBody List<Map<String, Object>> brandDataList) {
        dataService.saveBrands(brandDataList);
        return;
    }
    @ApiOperation(value = "Product data 수신", notes = "Product data 수신")
    @PostMapping("/saveProducts")
    public void saveProducts(@RequestBody List<Map<String, Object>> productDataList) {
        dataService.saveProducts(productDataList);
    }

}
