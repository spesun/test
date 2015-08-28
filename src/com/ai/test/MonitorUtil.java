package com.ai.test;

import info.ganglia.gmetric4j.gmetric.GMetric;
import info.ganglia.gmetric4j.gmetric.GMetricSlope;
import info.ganglia.gmetric4j.gmetric.GMetricType;
import info.ganglia.gmetric4j.gmetric.GangliaException;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorUtil {

    private static final Logger logger = LoggerFactory.getLogger(MonitorUtil.class);

    
    static String GANGLIA_HOST = "horton151";
    static int GANGLIA_PORT =  8660;
    static int GANGLIA_TTL = 1;
    
    static GMetric G_METRIC = null;
    static {
        
    	logger.info("[GANGLIA MONITOR] begin to init gmetric");
    	logger.info("[GANGLIA MONITOR] GANGLIA_HOST : " + GANGLIA_HOST);
    	logger.info("[GANGLIA MONITOR] GANGLIA_PORT : " + GANGLIA_PORT);
    	logger.info("[GANGLIA MONITOR] GANGLIA_TTL : " + GANGLIA_TTL);
        
        
        try {
			G_METRIC = new GMetric(GANGLIA_HOST, GANGLIA_PORT,
			        GMetric.UDPAddressingMode.UNICAST, GANGLIA_TTL, true);
		} catch (IOException e) {
			logger.error("", e);
		}
    }
    
   

    private static GMetric getGMetric() {
        return G_METRIC;
    }

    /**
     * monitor a metric
     * @param name Name of the metric
     * @param value Value of the metric
     * @param type Type of the metric.  
     *        Either string|int8|uint8|int16|uint16|int32|uint32|float|double
     * @param units Unit of measure for the value
     * @param slope Either zero|positive|negative|both
     * @param group Group Name of the metric
     */
    public static void monitor(String name, String value, GMetricType type,
                               String units,  GMetricSlope slope, String group) {
        GMetric gm = getGMetric();
        try {
            gm.announce(name, value, type, units, slope, 60, 0,  group);
        } catch (GangliaException e) {
        	e.printStackTrace();
            logger.error(String.format("monitor fail. name : %s, value:%s, error:%s",
            name, value, e.getMessage()), e);
        }

    }

    /**
     * monitor a metric
     * @param name Name of the metric
     * @param value Value of the metric
     * @param group Group Name of the metric
     */
    public static void monitor(String name,  String value, String group){
        monitor(name, value, GMetricType.STRING, " ", GMetricSlope.BOTH, group);
    }

    /**
     * monitor a positive metric
     * @param name Name of the metric
     * @param value Value of the metric
     * @param group Group Name of the metric
     */
    public static void counter(String name,  String value, String group){
        monitor(name, value, GMetricType.STRING, " ", GMetricSlope.POSITIVE, group);
    }

    /**
     * monitor a metric
     * @param name Name of the metric
     * @param value Value of the metric
     * @param group Group Name of the metric
     */
    public static void monitor(String name,  float value, String group){
        monitor(name, Float.toString(value),GMetricType.DOUBLE, " ", GMetricSlope.BOTH, group);
    }

    /**
     * monitor a positive metric
     * @param name Name of the metric
     * @param value Value of the metric
     * @param group Group Name of the metric
     */
    public static void counter(String name, float value, String group){
        monitor(name, Float.toString(value),GMetricType.FLOAT, " ", GMetricSlope.POSITIVE, group);
    }

}