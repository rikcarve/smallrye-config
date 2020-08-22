package io.smallrye.config.source.consul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ConfigurationTest {

    @Test
    public void testGetValidity() {
        Configuration config = new Configuration();
        assertEquals(30000, config.getValidity());
    }

    @Test
    public void testGetValidity_fromSys() {
        System.setProperty("configsource.consul.validity", "10");
        Configuration config = new Configuration();
        assertEquals(10000, config.getValidity());
        System.clearProperty("configsource.consul.validity");
    }

    @Test
    public void testGetPrefix() {
        Configuration config = new Configuration();
        assertEquals("", config.getPrefix());
    }

    @Test
    public void testGetPrefix_withSlash() {
        System.setProperty("configsource.consul.prefix", "jax");
        Configuration config = new Configuration();
        assertEquals("jax/", config.getPrefix());
        System.clearProperty("configsource.consul.prefix");
    }

    @Test
    public void testGetPrefix_withSubstitution() {
        System.setProperty("configsource.consul.prefix", "applications/${appname}");
        System.setProperty("appname", "jax");
        Configuration config = new Configuration();
        assertEquals("applications/jax/", config.getPrefix());
        System.clearProperty("configsource.consul.prefix");
        System.clearProperty("appName");
    }

    @Test
    public void testGetConsulHost() {
        Configuration config = new Configuration();
        assertEquals("", config.getConsulHost());
    }

    @Test
    public void testGetConsulHost_fromSys() {
        System.setProperty("configsource.consul.host", "jax");
        Configuration config = new Configuration();
        assertEquals("jax", config.getConsulHost());
        System.clearProperty("configsource.consul.host");
    }

    @Test
    public void testGetToken() {
        System.setProperty("configsource.consul.token", "token");
        Configuration config = new Configuration();
        assertEquals("token", config.getToken());
        System.clearProperty("configsource.consul.token");
    }

    @Test
    public void testGetToken_not_configured() {
        Configuration config = new Configuration();
        assertNull(config.getToken());
    }

    @Test
    public void testGetConsulHost_fromSys_withSubstitution() {
        System.setProperty("configsource.consul.host", "${docker.host}");
        System.setProperty("docker.host", "sub");
        Configuration config = new Configuration();
        assertEquals("sub", config.getConsulHost());
        System.clearProperty("configsource.consul.host");
        System.clearProperty("docker.host");
    }

}
