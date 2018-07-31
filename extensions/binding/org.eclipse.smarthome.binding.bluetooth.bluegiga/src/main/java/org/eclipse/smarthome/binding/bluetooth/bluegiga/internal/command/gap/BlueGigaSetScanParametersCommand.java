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
package org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.command.gap;

import org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.BlueGigaCommand;

/**
 * Class to implement the BlueGiga command <b>setScanParameters</b>.
 * <p>
 * This command sets the scan parameters which affect how other Smart devices are discovered.
 * <p>
 * This class provides methods for processing BlueGiga API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class BlueGigaSetScanParametersCommand extends BlueGigaCommand {
    public static int COMMAND_CLASS = 0x06;
    public static int COMMAND_METHOD = 0x07;

    /**
     * Scan interval defines the interval when scanning is re-started in units of 625us. Range: 0x4
     * - 0x4000. Default: (46,875ms) 0x4B After every scan interval the scanner will change the
     * frequency it operates at at it will cycle through all the three advertisements channels in a
     * round robin fashion. According to the specification all three channels must be Bluetooth
     * used by a scanner.
     * <p>
     * BlueGiga API type is <i>uint16</i> - Java type is {@link int}
     */
    private int scanInterval;

    /**
     * Scan Window defines how long time the scanner will listen on a certain frequency and try to
     * pick up advertisement packets. Scan window is defined as units of 625us. Range: 0x4 - 0x4000.
     * Default: 0x32 (31,25 ms). Scan windows must be equal or smaller than scan interval If scan
     * window is equal to the scan interval value, then the module Bluetooth will be scanning at a
     * 100% duty cycle. If scan window is half of the scan interval value, then the module Bluetooth
     * will be scanning at a 50% duty cycle.
     * <p>
     * BlueGiga API type is <i>uint16</i> - Java type is {@link int}
     */
    private int scanWindow;

    /**
     * 1: Active scanning is used. When an advertisement packet is received the Bluetooth stack
     * will send a scan request packet to the advertiser to try and read the scan response data. 0:
     * Passive scanning is used. No scan request is made.
     * <p>
     * BlueGiga API type is <i>boolean</i> - Java type is {@link boolean}
     */
    private boolean activeScanning;

    /**
     * Scan interval defines the interval when scanning is re-started in units of 625us. Range: 0x4
     * - 0x4000. Default: (46,875ms) 0x4B After every scan interval the scanner will change the
     * frequency it operates at at it will cycle through all the three advertisements channels in a
     * round robin fashion. According to the specification all three channels must be Bluetooth
     * used by a scanner.
     *
     * @param scanInterval the scanInterval to set as {@link int}
     */
    public void setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
    }
    /**
     * Scan Window defines how long time the scanner will listen on a certain frequency and try to
     * pick up advertisement packets. Scan window is defined as units of 625us. Range: 0x4 - 0x4000.
     * Default: 0x32 (31,25 ms). Scan windows must be equal or smaller than scan interval If scan
     * window is equal to the scan interval value, then the module Bluetooth will be scanning at a
     * 100% duty cycle. If scan window is half of the scan interval value, then the module Bluetooth
     * will be scanning at a 50% duty cycle.
     *
     * @param scanWindow the scanWindow to set as {@link int}
     */
    public void setScanWindow(int scanWindow) {
        this.scanWindow = scanWindow;
    }
    /**
     * 1: Active scanning is used. When an advertisement packet is received the Bluetooth stack
     * will send a scan request packet to the advertiser to try and read the scan response data. 0:
     * Passive scanning is used. No scan request is made.
     *
     * @param activeScanning the activeScanning to set as {@link boolean}
     */
    public void setActiveScanning(boolean activeScanning) {
        this.activeScanning = activeScanning;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(COMMAND_CLASS, COMMAND_METHOD);

        // Serialize the fields
        serializeUInt16(scanInterval);
        serializeUInt16(scanWindow);
        serializeBoolean(activeScanning);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BlueGigaSetScanParametersCommand [scanInterval=");
        builder.append(scanInterval);
        builder.append(", scanWindow=");
        builder.append(scanWindow);
        builder.append(", activeScanning=");
        builder.append(activeScanning);
        builder.append(']');
        return builder.toString();
    }
}
