package com.microgrid;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class AgentTest {

    @Inject
    EmbeddedApplication<?> application;

    // No unit tests for the time being
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

}
