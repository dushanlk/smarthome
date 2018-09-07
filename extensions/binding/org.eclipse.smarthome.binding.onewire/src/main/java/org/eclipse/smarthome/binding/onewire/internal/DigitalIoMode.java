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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link DigitalIoMode} provides the direction of a digital IO channel
 *
 * @author Jan N. Klug - Initial contribution
 */
@NonNullByDefault
public enum DigitalIoMode {
    INPUT,
    OUTPUT;
}
