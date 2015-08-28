package com.ai.test.ganglia;

import java.io.IOException;

import info.ganglia.gmetric4j.gmetric.GMetric;
import info.ganglia.gmetric4j.gmetric.GangliaException;

public class TestGmetric4j {

	
	public static void main(String[] args) throws IOException, GangliaException {
		String ganglia_host = "horton151";
	    int ganglia_port =  8660;
	    int ganglia_ttl = 1;
	    GMetric g_metric = new GMetric(ganglia_host, ganglia_port,
		        GMetric.UDPAddressingMode.UNICAST, ganglia_ttl, true);
	    
	    g_metric.announce("test_int_3", 4444, "");
	    
	    g_metric.close();
	}
}
