package com.starwars.resistancesocialnetwork.gateways.controllers.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeadquarterRequest {
//    private Long id;
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50)
    @ApiModelProperty(required = true, value="Headquarters's name", example = "Headquarter A")
    private String name;
    @NotNull
    @ApiModelProperty(required = true, value="Headquarters's location coordinates", example = "{45, 29}")
    private Point location;
    @Valid
    @NotEmpty
    private List<RebelRequest> rebels;
}
