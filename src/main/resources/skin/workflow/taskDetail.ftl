    <div id="#taskDetails" class="panel">
      <#if msg != null>${msg}</#if>
      <h1>Task References</h1>
      <fieldset>
        <div class="row">
          <label>Action type</label>
          <span>${task.getDescription()}</span>
        </div>
        <div class="row">
          <label>Due Date</label>
          <span>${task.getDueDate()}</span>
        </div>
      </fieldset>
      <div id="accept" class="whiteButton" href="#" onClick="nxDoActionWithGoBackViewElements('${basePath}/mobile/workflow/byId/${task.getId()?string("#############")}?action=accept')">Accept</div>
      <div id="deny" class="whiteButton" href="#" onClick="nxDoActionWithGoBackViewElements('${basePath}/mobile/workflow/byId/${task.getId()?string("#############")}?action=deny')">Deny</div>
      <form>
        <input type="hidden" name="TbBModHIDE_help" value="true" />
      </form>
    </div>
