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
package
/*
 * generated by Xtext
 */
org.eclipse.smarthome.model

import com.google.inject.Binder
import com.google.inject.name.Names
import org.eclipse.smarthome.model.internal.valueconverter.ItemValueConverters
import org.eclipse.xtext.conversion.IValueConverterService
import org.eclipse.xtext.linking.lazy.LazyURIEncoder

/** 
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ItemsRuntimeModule extends AbstractItemsRuntimeModule {
    
    override Class<? extends IValueConverterService> bindIValueConverterService() {
        return ItemValueConverters
    }
    
    override void configureUseIndexFragmentsForLazyLinking(Binder binder) {
        binder.bind(Boolean.TYPE).annotatedWith(Names.named(LazyURIEncoder.USE_INDEXED_FRAGMENTS_BINDING)).toInstance(
            Boolean.FALSE)
    }
}
