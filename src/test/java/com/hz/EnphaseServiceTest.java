package com.hz;

import com.hz.models.envoy.json.EimType;
import com.hz.models.envoy.json.Production;
import com.hz.models.envoy.json.System;
import com.hz.models.envoy.json.TypeBase;
import com.hz.services.EnphaseService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EnphaseServiceTest {

	@Mock
	private EnphaseService mockEnphaseService;

	private Optional<System> makeSystem(LocalDateTime now) {
		Optional<System> system = Optional.of(new System());

		system.get().setProduction(new Production());
		system.get().getProduction().setProductionList(new ArrayList<>());
		system.get().getProduction().getProductionList().add(new EimType());
		Optional<TypeBase> productionEim = system.get().getProduction().getProductionEim();
		productionEim.ifPresent(typeBase -> typeBase.setReadingTime(now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000L));

		return system;
	}

	@Test
	public void verifyCollectionDate() {
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

		// Given
		Mockito.when(this.mockEnphaseService.collectEnphaseData()).thenReturn(makeSystem(now));
		Mockito.when(this.mockEnphaseService.getCollectionTime(any(System.class))).thenCallRealMethod();
		// When
		LocalDateTime collectionTime = this.mockEnphaseService.getCollectionTime(this.mockEnphaseService.collectEnphaseData().get());
		// Then
		Assert.assertThat(collectionTime, Matchers.equalTo(now));
	}
}