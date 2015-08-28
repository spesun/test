package com.ai.test.ganglia;

import info.ganglia.gmetric4j.gmetric.GMetric;
import info.ganglia.gmetric4j.gmetric.GMetric.UDPAddressingMode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ganglia.GangliaReporter;

public class TestGanglia {

	@Test
	public void sendMetrics() throws IOException {

		MetricRegistry metrics = new MetricRegistry();

		final GMetric ganglia = new GMetric("ganglia.example.com", 8649,
				UDPAddressingMode.MULTICAST, 1);
		final GangliaReporter reporter = GangliaReporter.forRegistry(metrics)
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build(ganglia);
		reporter.start(1, TimeUnit.MINUTES);
	}

	static final MetricRegistry metrics = new MetricRegistry();

	static void startReport() {
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.SECONDS);
	}

	static void wait5Seconds() {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test
	public void sendConsole() throws IOException {
		startReport();
		Meter requests = metrics.meter("requests");
		requests.mark();
		wait5Seconds();
	}

	
}
