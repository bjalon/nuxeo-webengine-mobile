var docItemTouched = null;
var x_orig = 0;
var y_orig = 0;

function addListenerForGestureOnItemInPage(docid) {
  var items = x$('#' + docid).find('.nxDocumentItem');

  items.on('touchstart', function(event) {
    x_orig = event.touches[0].screenX;
    y_orig = event.touches[0].screenY;
  });

  items.on('touchmove', function(event) {
    var delta_x = x_orig - event.touches[0].screenX;
    var delta_y = x_orig - event.touches[0].screenY;
    if (delta_x < - 60 && delta_y > - 40 && delta_y < 40) {
      var element = x$(event.target.parentNode.parentNode).find('.deleteButton');
      if (element.hasClass('hiddenDeleteButton')) {
        element.removeClass('hiddenDeleteButton');
        element.addClass('displayedDeleteButton');
      } else {
          if (element.hasClass('displayedDeleteButton')) {
            element.removeClass('displayedDeleteButton');
            element.addClass('hiddenDeleteButton');
          }
      }
    }
  });

}
