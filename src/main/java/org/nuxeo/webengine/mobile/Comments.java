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

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.platform.comment.api.CommentableDocument;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 */
@WebObject(type = "comments")
@Produces("text/html;charset=UTF-8")
public class Comments extends DefaultObject {

    @GET
    @Path("{docId}")
    public Object getComments(@PathParam("docId") String docId)
            throws ClientException {
        CoreSession session = WebEngine.getActiveContext().getCoreSession();
        DocumentModel doc = session.getDocument(new IdRef(docId));
        CommentableDocument commentableDocument = doc.getAdapter(CommentableDocument.class);

        List<DocumentModel> comments = commentableDocument.getComments();
        return getTemplate("comments/document_comments.ftl").arg("comments",
                comments);
    }

}
