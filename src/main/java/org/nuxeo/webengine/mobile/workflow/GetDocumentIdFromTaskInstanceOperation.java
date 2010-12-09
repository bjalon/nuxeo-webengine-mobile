package org.nuxeo.webengine.mobile.workflow;

import java.io.Serializable;

import org.jbpm.JbpmContext;
import org.nuxeo.ecm.platform.jbpm.NuxeoJbpmException;

public class GetDocumentIdFromTaskInstanceOperation {

    private static final long serialVersionUID = 1L;

    protected long id;

    protected String documentId;


    public GetDocumentIdFromTaskInstanceOperation(long id) {
        this.id = id;
    }

    public Serializable run(JbpmContext context) throws NuxeoJbpmException {
//        TaskInstance ti = context.getTaskInstanceForUpdate(id);
//        ti.addComment(comment);
        return null;
    }


}
