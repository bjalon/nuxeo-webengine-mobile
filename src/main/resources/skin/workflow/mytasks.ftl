    <ul id="myTasks" title="My Tasks">
      <#if tasks?size == 0>
        <li>No Task found</li>
      </#if>
      <#list tasks as task>
        <#assign date = task.getDueDate()>
        <#if date == "">
          <li><a href="${basePath}/mobile/workflow/byId/${task.getId()?string("#############")}">${task.getName()} (no due date)</a></li>
        <#else>
          <li><a href="${basePath}/mobile/workflow/byId/${task.getId()?string("#############")}">${task.getName()} waited for ${date}</a></li>
        </#if>
      </#list>
    </ul>
