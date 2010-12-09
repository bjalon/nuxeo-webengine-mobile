package org.nuxeo.webengine.mobile.workflow;

import java.util.ArrayList;

import org.jbpm.JbpmContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.jbpm.JbpmListFilter;

public class TaskInstanceIdFilter implements JbpmListFilter {

    private static final long serialVersionUID = 1L;

    protected long id;

    public TaskInstanceIdFilter(long id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> filter(JbpmContext jbpmContext,
            DocumentModel document, ArrayList<T> list, NuxeoPrincipal principal) {
        ArrayList<TaskInstance> result = new ArrayList<TaskInstance>();
        for (T t : list) {
            TaskInstance task = (TaskInstance) t;
            if (task.getId() == id) {
                result.add(task);
            }
        }
        return (ArrayList<T>) result;
    }
}
