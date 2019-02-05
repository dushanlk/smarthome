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
package org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.command.system;

import org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.BlueGigaResponse;

/**
 * Class to implement the BlueGiga command <b>getConnections</b>.
 * <p>
 * This command reads the number of supported connections from the local device.
 * <p>
 * This class provides methods for processing BlueGiga API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class BlueGigaGetConnectionsResponse extends BlueGigaResponse {
    public static int COMMAND_CLASS = 0x00;
    public static int COMMAND_METHOD = 0x06;

    /**
     * Max supported connections
     * <p>
     * BlueGiga API type is <i>uint8</i> - Java type is {@link int}
     */
    private int maxconn;

    /**
     * Response constructor
     */
    public BlueGigaGetConnectionsResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        event = (inputBuffer[0] & 0x80) != 0;

        // Deserialize the fields
        maxconn = deserializeUInt8();
    }

    /**
     * Max supported connections
     * <p>
     * BlueGiga API type is <i>uint8</i> - Java type is {@link int}
     *
     * @return the current maxconn as {@link int}
     */
    public int getMaxconn() {
        return maxconn;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BlueGigaGetConnectionsResponse [maxconn=");
        builder.append(maxconn);
        builder.append(']');
        return builder.toString();
    }
}
