    <form title="Edit" class="panel">
      <script>
        cleanFormValuesOnBlur();
        // TODO : Update the DatePicker value with the right date
      </script>
      <fieldset>
        <div class="row">
          <label>Title</label>
          <input name="dc-title" type="text" value="${Document.dublincore.title}"/>
        </div>
        <div class="row">
          <label>Description</label>
          <textarea name="dc-description" rows="15">${Document.dublincore.description}</textarea>
        </div>
        <div class="row">
          <label>Issued date</label>
          <input name="dc-issued" class="date today" value="" />
        </div>
      </fieldset>
      <div class="whiteButton" onClick="nxUpdateDocumentFormAndGoBack('${Document.id}')">Save</div>
      <input type="hidden" name="TbBModHIDE_help" value="true" />
    </form>
