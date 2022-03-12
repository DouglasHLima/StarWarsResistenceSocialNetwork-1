package com.starwars.resistancesocialnetwork.gateways.controllers;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.controllers.mappers.request.HeadquarterRequestMapper;
import com.starwars.resistancesocialnetwork.gateways.controllers.mappers.response.HeadquarterResponseMapper;
import com.starwars.resistancesocialnetwork.gateways.controllers.request.HeadquarterRequest;
import com.starwars.resistancesocialnetwork.gateways.controllers.response.HeadquarterResponse;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterCreateService;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterDeleteService;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterGetService;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/headquarters")
@RequiredArgsConstructor
public class HeadquarterController {
  private final HeadquarterRequestMapper headquarterRequestMapper =
      HeadquarterRequestMapper.INSTANCE;
  private final HeadquarterResponseMapper headquarterResponseMapper =
      HeadquarterResponseMapper.INSTANCE;
  private final HeadquarterCreateService createService;
  private final HeadquarterDeleteService deleteService;
  private final HeadquarterGetService getService;
  private final HeadquarterUpdateService updateService;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public HeadquarterResponse getHeadquarterById(@PathVariable("id") Long id){
    Headquarter foundById = getService.getById(id);
    return headquarterResponseMapper.toResponse(foundById);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<HeadquarterResponse> getAllHeadquarters(Pageable page) {
    Page<Headquarter> foundAll = getService.getAll(page);
    return new PageImpl<>(
        foundAll.getContent().stream()
            .map(headquarterResponseMapper::toResponse)
            .collect(Collectors.toList()));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public HeadquarterResponse createHeadquarter(
      @Valid @RequestBody HeadquarterRequest headquarterRequest) {
    Headquarter headquarter = headquarterRequestMapper.toDomain(headquarterRequest);
    Headquarter saved = createService.execute(headquarter);
    return headquarterResponseMapper.toResponse(saved);
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public HeadquarterResponse updateHeadquarter(
      @PathVariable("id") Long id, @Valid @RequestBody HeadquarterRequest headquarterRequest){
    Headquarter headquarter = headquarterRequestMapper.toDomain(headquarterRequest);
    Headquarter headquarterResponse = updateService.execute(id, headquarter);
    return headquarterResponseMapper.toResponse(headquarterResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("id") Long id){
    deleteService.execute(id);
  }
}
