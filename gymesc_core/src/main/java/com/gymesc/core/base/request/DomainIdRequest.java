package com.gymesc.core.base.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DomainIdRequest {

    @NotNull
    private Long id;

}
