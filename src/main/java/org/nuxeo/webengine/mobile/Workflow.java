package org.nuxeo.webengine.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.jbpm.JbpmService;
import org.nuxeo.ecm.platform.jbpm.JbpmTaskService;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.webengine.mobile.workflow.TaskInstanceIdFilter;

@WebObject(type = "workflow")
@Produces("text/html;charset=UTF-8")
public class Workflow extends DefaultObject {

    protected Log log = LogFactory.getLog(Workflow.class);

    protected static final String ACCEPT = "accept";

    protected static final String DENY = "deny";

    @GET
    public Object doGet() throws Exception {
        return getTemplate("workflow/index.ftl");
    }

    @GET
    @Path("myTask")
    public Object doGetMyTasks() throws Exception {
        JbpmService service = Framework.getService(JbpmService.class);
        NuxeoPrincipal principal = (NuxeoPrincipal) WebEngine.getActiveContext().getPrincipal();
        List<TaskInstance> tasks = service.getCurrentTaskInstances(principal,
                null);
        return getTemplate("workflow/mytasks.ftl").arg("tasks", tasks).arg(
                "numberOfTasks", tasks.size());
    }

    @GET
    @Path("byId/{id}")
    public Object doGetTaskById(@PathParam("id") Long taskId,
            @QueryParam("action") String action,
            @QueryParam("comment") String comment) throws Exception {
        JbpmService service = Framework.getService(JbpmService.class);
        NuxeoPrincipal principal = (NuxeoPrincipal) WebEngine.getActiveContext().getPrincipal();
        List<TaskInstance> tasks = service.getCurrentTaskInstances(principal,
                new TaskInstanceIdFilter(taskId));

        if (tasks.size() != 1) {
            throw new Exception("No task instance found with this given id : "
                    + taskId);
        }
        String message = null;
        TaskInstance task = tasks.get(0);
        if (action != null) {
            log.debug("Trying to " + action + "on task instance : " + task);
            CoreSession session = WebEngine.getActiveContext().getCoreSession();
            JbpmTaskService taskService = Framework.getService(JbpmTaskService.class);
            if (ACCEPT.equals(action) || DENY.equals(action)) {
                try {
                    if (ACCEPT.equals(action)) {
                        taskService.acceptTask(session, principal, task,
                                comment);
                    } else {
                        taskService.rejectTask(session, principal, task,
                                comment);
                    }
                } catch (Exception e) {
                    // TODO : waiting a clean fix of jbpmservice
//                    log.error(e, e);
//                    task = (TaskInstance) ((Template) doGetTaskById(taskId,
//                            null, null)).args().get("task");
//                    if (task != null) {
//                        log.error(e, e);
//                        message = "Action asked can't be executed since an Exception is occured, please call your Administrator";
//                        return message;
//                    }
                }
                return "OK";
            }
        }
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("task", task);
        args.put("msg", message);
        return getTemplate("workflow/taskDetail.ftl").args(args);

    }
}
