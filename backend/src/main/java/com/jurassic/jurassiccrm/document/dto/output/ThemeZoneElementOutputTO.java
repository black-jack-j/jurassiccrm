package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeZoneElementOutputTO {
    private SimpleEntityOutputTO type;
    private Integer number;
}
