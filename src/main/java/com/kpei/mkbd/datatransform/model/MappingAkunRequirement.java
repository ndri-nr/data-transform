package com.kpei.mkbd.datatransform.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MappingAkunRequirement {
    private String kode;
    private String group;
    private String[] requirements;
}
