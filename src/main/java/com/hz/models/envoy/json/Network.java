package com.hz.models.envoy.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Network {
	@JsonProperty(value="last_enlighten_report_time")
	private long lastReportTime;
	@JsonProperty(value="primary_interface")
	private String primaryInterface;

	@JsonProperty(value="interfaces")
	private List<NetInterface> netInterfaces;

	public Date getLastReportTime() {
		return new Date(lastReportTime * 1000L);
	}

	public Optional<NetInterface> findPrimary() {
		return netInterfaces.stream().filter(netInterface -> netInterface.getInterfaceName().equalsIgnoreCase(primaryInterface)).findFirst();
	}

	public boolean isWifi() {
		Optional<NetInterface> netInterface = findPrimary();

		return (netInterface.isPresent() ? netInterface.get().getType().equalsIgnoreCase("wifi") : false);
	}

}