package org.nuxeo.webengine.mobile;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;
import org.nuxeo.webengine.mobile.helper.MobileHelper;

@WebObject(type = "navigation")
@Produces("text/html;charset=UTF-8")
public class Navigation extends DefaultObject {

    protected static final String ROOT_PATH = "/";

    @Path("displayNewDocumentForm/{parentId}")
    @GET
    public Object doCreateNewDocument(@PathParam("parentId") String parentId) throws Exception {
        DocumentModel doc = WebEngine.getActiveContext().getCoreSession().getDocument(new IdRef(parentId));
        List<String> docTypes = MobileHelper.getSubTypes(doc.getType());
        return getTemplate("new_document_form.ftl").arg("parentId", parentId).arg("docTypes", docTypes);
    }

    @Path("root")
    public Object doGet() throws Exception {
        return new MobileDocument(getContext(), ROOT_PATH);
    }

    @Path("byId/{id}")
    public Object doGetDocumentById(@PathParam("id") String docId) {
        return new MobileDocument(ctx, new IdRef(docId));
    }

}
