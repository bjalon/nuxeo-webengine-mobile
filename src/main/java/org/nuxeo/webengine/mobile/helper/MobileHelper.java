/*
 * (C) Copyright 2010 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * Contributors:
 * Nuxeo - initial API and implementation
 */

package org.nuxeo.webengine.mobile.helper;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.platform.types.SubType;
import org.nuxeo.ecm.platform.types.Type;
import org.nuxeo.ecm.platform.types.TypeManager;
import org.nuxeo.runtime.api.Framework;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 */
public class MobileHelper {

    private MobileHelper() {
        // Helper class
    }

    protected static TypeManager geTypeManager() throws ClientException {
        TypeManager typeManager;
        try {
            typeManager = Framework.getService(TypeManager.class);
        } catch (Exception e) {
            final String errMsg = "Error connecting to TypeManager. "
                    + e.getMessage();
            throw new ClientException(errMsg, e);
        }
        if (typeManager == null) {
            throw new ClientException("TypeManager service not bound");
        }
        return typeManager;
    }

    public static boolean hasSubTypes(String typeName) throws ClientException {
        return !getSubTypes(typeName).isEmpty();
    }

    public static List<String> getSubTypes(String typeName) throws ClientException {
        TypeManager typeManager = geTypeManager();
        List<String> subTypes = new ArrayList<String>();
        Type currentType = typeManager.getType(typeName);
        for (SubType subType : currentType.getAllowedSubTypes().values()) {
            List<String> subTypesHiddenInCreation = subType.getHidden();
            if (subTypesHiddenInCreation != null
                        && !subTypesHiddenInCreation.contains("create")) {
                    subTypes.add(subType.getName());
                }
        }
        return subTypes;
    }

}
