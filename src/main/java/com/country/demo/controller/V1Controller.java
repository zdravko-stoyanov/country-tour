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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static com.country.demo.util.Validator.validateGetNeighboursTourRequestDto;

@RestController
@RequestMapping(value = "v1")
public class V1Controller {

    private TourService tourService;
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public V1Controller(TourService tourService, OAuth2AuthorizedClientService authorizedClientService) {
        this.tourService = tourService;
        this.authorizedClientService = authorizedClientService;
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
