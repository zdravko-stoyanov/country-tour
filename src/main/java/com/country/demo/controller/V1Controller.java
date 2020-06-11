package com.country.demo.controller;

import com.country.demo.domain.enums.AvailableCurrency;
import com.country.demo.dto.ErrorDto;
import com.country.demo.dto.GetNeighboursTourRequestDto;
import com.country.demo.dto.GetNeighboursTourResponseDto;
import com.country.demo.service.TourService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.country.demo.util.Validator.validateGetNeighboursTourRequestDto;

@RestController
@RequestMapping(value = "v1")
public class V1Controller {

    private final TourService tourService;

    @Autowired
    public V1Controller(TourService tourService) {
        this.tourService = tourService;
    }

    @ApiResponses({
            @ApiResponse(description = "Ok", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("tourCalculation")
    public GetNeighboursTourResponseDto getTourCalculation(@RequestParam String startingCountry,
                                                           @RequestParam BigDecimal budgetPerCountry,
                                                           @RequestParam BigDecimal totalBudget,
                                                           @RequestParam AvailableCurrency currency) {


        GetNeighboursTourRequestDto request = new GetNeighboursTourRequestDto(startingCountry, budgetPerCountry, totalBudget, currency);

        validateGetNeighboursTourRequestDto(request);

        return tourService.getTourCalculation(request);
    }
}
