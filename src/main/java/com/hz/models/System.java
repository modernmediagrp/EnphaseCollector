package com.hz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by David on 22-Oct-17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class System {
    @JsonProperty(value="software_build_epoch")
    private Date softwareBuildEpoch;
    @JsonProperty(value="is_nonvoy")
    private boolean isNonvoy;
    @JsonProperty(value="db_size")
    private String databaseSize;
    @JsonProperty(value="db_percent_full")
    private int databasePercFull;
    private String timezone;
    @JsonProperty(value="current_date")
    private String currentDate;
    @JsonProperty(value="current_time")
    private String currentTime;
    private Comm comm;
    @JsonProperty(value="alerts")
    private List<String> alertList;
    @JsonProperty(value="update_status")
    private String updateStatus;

    private Production production;  // Populated from production.json
    private List<Inventory> inventoryList;  // populated from inventory.json
}
