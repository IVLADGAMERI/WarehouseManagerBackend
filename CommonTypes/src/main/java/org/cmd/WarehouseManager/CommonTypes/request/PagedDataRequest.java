package org.cmd.WarehouseManager.CommonTypes.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Setter
@Validated
public class PagedDataRequest {
    @PositiveOrZero
    public int pageIndex;
    @Range(min = 1, max = 20)
    public int pageSize;
    @NotBlank
    public String sortParam;
    @Range(min = -1, max = 1)
    public int sortDirection;
}
