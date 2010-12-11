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

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.annotations.api.Annotation;
import org.nuxeo.ecm.platform.annotations.api.AnnotationsService;
import org.nuxeo.ecm.platform.url.DocumentViewImpl;
import org.nuxeo.ecm.platform.url.api.DocumentView;
import org.nuxeo.ecm.platform.url.api.DocumentViewCodecManager;
import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;
import org.nuxeo.runtime.api.Framework;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 */
@WebObject(type = "annotations")
@Produces("text/html;charset=UTF-8")
public class Annotations extends DefaultObject {

    public static String DOCUMENT_PATH_CODED = "docpath";

    @GET
    @Path("{docId}")
    public Object getAnnotations(@PathParam("docId") String docId)
            throws Exception {
        AnnotationsService annotationsService = Framework.getService(AnnotationsService.class);
        DocumentViewCodecManager documentViewCodecManager = Framework.getService(DocumentViewCodecManager.class);

        WebContext context = WebEngine.getActiveContext();
        CoreSession session = context.getCoreSession();
        DocumentModel doc = session.getDocument(new IdRef(docId));

        DocumentView docView = new DocumentViewImpl(doc);
        String documentUrl = documentViewCodecManager.getUrlFromDocumentView(
                DOCUMENT_PATH_CODED, docView, true,
                VirtualHostHelper.getBaseURL(context.getRequest()));

        List<Annotation> annotations = annotationsService.queryAnnotations(
                new URI(documentUrl), null,
                (NuxeoPrincipal) session.getPrincipal());
        return getTemplate("annotations/document_annotations.ftl").arg(
                "annotations", annotations);
    }

}
