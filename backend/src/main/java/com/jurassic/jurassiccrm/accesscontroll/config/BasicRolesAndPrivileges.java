package com.jurassic.jurassiccrm.accesscontroll.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "jurassic")
public class BasicRolesAndPrivileges {

    private Map<String, List<String>> rolesToPrivileges = new HashMap<>();

}
