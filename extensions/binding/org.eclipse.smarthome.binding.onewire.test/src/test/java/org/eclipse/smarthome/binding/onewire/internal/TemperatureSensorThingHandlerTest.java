/**
 * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.binding.onewire.internal;

import static org.eclipse.smarthome.binding.onewire.internal.OwBindingConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

import org.eclipse.smarthome.binding.onewire.internal.handler.TemperatureSensorThingHandler;
import org.eclipse.smarthome.binding.onewire.test.AbstractThingHandlerTest;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.binding.builder.ThingBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * Tests cases for {@link DigitalIOThingeHandler}.
 *
 * @author Jan N. Klug - Initial contribution
 */
public class TemperatureSensorThingHandlerTest extends AbstractThingHandlerTest {
    private static final String TEST_ID = "00.000000000000";
    private static final ThingUID THING_UID = new ThingUID(THING_TYPE_TEMPERATURE, "testthing");
    private static final ChannelUID CHANNEL_UID_PRESENT = new ChannelUID(THING_UID, CHANNEL_PRESENT);
    private static final ChannelUID CHANNEL_UID_TEMPERATURE = new ChannelUID(THING_UID, CHANNEL_TEMPERATURE);

    protected Channel channel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        initializeBridge();

        thingConfiguration.put(CONFIG_ID, TEST_ID);
        thingProperties.put(PROPERTY_SENSORCOUNT, "1");
        thingProperties.put(PROPERTY_MODELID, "UNKNOWN");
        thingProperties.put(PROPERTY_VENDOR, "generic");

        channels.add(ChannelBuilder.create(CHANNEL_UID_PRESENT, "Switch").build());
        channels.add(ChannelBuilder.create(CHANNEL_UID_TEMPERATURE, "Number").build());

        thing = ThingBuilder.create(THING_TYPE_TEMPERATURE, "testthing").withLabel("Test thing").withChannels(channels)
                .withConfiguration(new Configuration(thingConfiguration)).withProperties(thingProperties)
                .withBridge(bridge.getUID()).build();

        thingHandler = new TemperatureSensorThingHandler(thing, stateProvider) {
            @Override
            protected Bridge getBridge() {
                return bridge;
            }
        };

        initializeHandlerMocks();

    }

    @Test
    public void testInitializationEndsWithUnknown() {
        thingHandler.initialize();

        waitForAssert(() -> assertEquals(ThingStatus.UNKNOWN, thing.getStatusInfo().getStatus()));
    }

    @Test
    public void testRefresh() {
        thingHandler.initialize();
        thingHandler.refresh(bridgeHandler, System.currentTimeMillis());
        try {
            inOrder.verify(bridgeHandler, times(1)).checkPresence(TEST_ID);
            inOrder.verify(bridgeHandler, times(1)).readDecimalType(eq(TEST_ID), any());

            inOrder.verifyNoMoreInteractions();
        } catch (OwException e) {
            Assert.fail("caught unexpected OwException");
        }
    }
}
