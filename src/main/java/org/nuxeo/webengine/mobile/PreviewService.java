package org.nuxeo.webengine.mobile;

import javax.ws.rs.GET;

import org.nuxeo.ecm.platform.preview.api.PreviewException;
import org.nuxeo.ecm.webengine.model.WebAdapter;
import org.nuxeo.ecm.webengine.model.impl.DefaultAdapter;

@WebAdapter(name = "preview", type = "PreviewService", targetType = "Document")
public class PreviewService extends DefaultAdapter {

    @GET
    public Object doGet() throws PreviewException {
            return getTemplate("preview/preview.ftl");
    }

}
