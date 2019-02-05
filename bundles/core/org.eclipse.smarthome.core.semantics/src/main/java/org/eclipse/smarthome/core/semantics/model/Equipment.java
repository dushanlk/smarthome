/**
 * Copyright (c) 2014,2019 Contributors to the Eclipse Foundation
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
package org.eclipse.smarthome.core.semantics.model;

/**
 * This is the super interface for all types that represent an Equipment.
 * The interface describes the relations to other entity types.
 *
 * @author Kai Kreuzer - Initial contribution
 *
 */
@TagInfo(id = "Equipment")
public interface Equipment extends Tag {

    Equipment isPartOf();

    Location hasLocation();

}
