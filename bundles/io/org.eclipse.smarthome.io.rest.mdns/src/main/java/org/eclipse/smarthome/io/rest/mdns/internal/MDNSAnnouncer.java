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
package org.eclipse.smarthome.io.rest.mdns.internal;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.smarthome.core.net.HttpServiceUtil;
import org.eclipse.smarthome.io.rest.RESTConstants;
import org.eclipse.smarthome.io.transport.mdns.MDNSService;
import org.eclipse.smarthome.io.transport.mdns.ServiceDescription;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * This class announces the REST API through mDNS for clients to automatically
 * discover it.
 *
 * @author Kai Kreuzer - Initial contribution and API
 * @author Markus Rathgeb - Use HTTP service utility functions
 */
@Component(immediate = true, configurationPid = "org.eclipse.smarthome.mdns", property = {
        Constants.SERVICE_PID + "=org.eclipse.smarthome.mdns" //
})
public class MDNSAnnouncer {

    private int httpSSLPort;

    private int httpPort;

    private String mdnsName;

    private MDNSService mdnsService;

    @Reference(policy = ReferencePolicy.DYNAMIC)
    public void setMDNSService(MDNSService mdnsService) {
        this.mdnsService = mdnsService;
    }

    public void unsetMDNSService(MDNSService mdnsService) {
        this.mdnsService = null;
    }

    @Activate
    public void activate(BundleContext bundleContext, Map<String, Object> properties) {
        if (!"false".equalsIgnoreCase((String) properties.get("enabled"))) {
            if (mdnsService != null) {
                mdnsName = bundleContext.getProperty("mdnsName");
                if (mdnsName == null) {
                    mdnsName = "smarthome";
                }
                try {
                    httpPort = HttpServiceUtil.getHttpServicePort(bundleContext);
                    if (httpPort != -1) {
                        mdnsService.registerService(getDefaultServiceDescription());
                    }
                } catch (NumberFormatException e) {
                }
                try {
                    httpSSLPort = HttpServiceUtil.getHttpServicePortSecure(bundleContext);
                    if (httpSSLPort != -1) {
                        mdnsService.registerService(getSSLServiceDescription());
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    @Deactivate
    public void deactivate() {
        if (mdnsService != null) {
            mdnsService.unregisterService(getDefaultServiceDescription());
            mdnsService.unregisterService(getSSLServiceDescription());
        }
    }

    private ServiceDescription getDefaultServiceDescription() {
        Hashtable<String, String> serviceProperties = new Hashtable<String, String>();
        serviceProperties.put("uri", RESTConstants.REST_URI);
        return new ServiceDescription("_" + mdnsName + "-server._tcp.local.", mdnsName, httpPort, serviceProperties);
    }

    private ServiceDescription getSSLServiceDescription() {
        ServiceDescription description = getDefaultServiceDescription();
        description.serviceType = "_" + mdnsName + "-server-ssl._tcp.local.";
        description.serviceName = "" + mdnsName + "-ssl";
        description.servicePort = httpSSLPort;
        return description;
    }
}
