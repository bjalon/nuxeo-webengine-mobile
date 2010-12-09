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

package org.nuxeo.webengine.mobile;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.Filter;
import org.nuxeo.ecm.core.api.impl.CompoundFilter;
import org.nuxeo.ecm.core.api.impl.FacetFilter;
import org.nuxeo.ecm.core.api.impl.LifeCycleFilter;
import org.nuxeo.ecm.core.rest.DocumentRoot;
import org.nuxeo.ecm.platform.preview.helper.PreviewHelper;
import org.nuxeo.ecm.platform.types.TypeManager;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.webengine.mobile.helper.MobileHelper;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 */
public class MobileDocument extends DocumentRoot {

    public static final Filter ONLY_VISIBLE_CHILDREN = new CompoundFilter(
            new FacetFilter(null,
                    Collections.singletonList("HiddenInNavigation")),
            new LifeCycleFilter(null, Collections.singletonList("deleted")));

    protected TypeManager typeManager;

    public MobileDocument(WebContext ctx, String uri) {
        super(ctx, uri);
    }

    public MobileDocument(WebContext ctx, DocumentRef root) {
        super(ctx, root);
    }

    public MobileDocument(WebContext ctx, DocumentModel root) {
        super(ctx, root);
    }

    public boolean hasSubTypes() throws ClientException {
        return MobileHelper.hasSubTypes(doc.getType());
    }

    public List<String> getSubTypes() throws ClientException {
        return MobileHelper.getSubTypes(doc.getType());
    }

    public List<DocumentModel> getVisibleChildren() throws ClientException {
        CoreSession session = WebEngine.getActiveContext().getCoreSession();
        return session.getChildren(doc.getRef(), null, ONLY_VISIBLE_CHILDREN,
                null);
    }

    @POST
    @Override
    public Response doPost() {
        super.doPost();
        return Response.ok().build();
    }

    @DELETE
    @Override
    public Response doDelete() {
        super.doDelete();
        return Response.ok().build();
    }

    @PUT
    @Override
    public Response doPut() {
        super.doPut();
        return Response.ok().build();
    }

    public boolean hasPreview() {
        return PreviewHelper.typeSupportsPreview(doc);
    }

    public String getPreviewURL() {
        return WebEngine.getActiveContext().getServerURL() + "/nuxeo/"
                + PreviewHelper.getPreviewURL(doc);
    }

}
