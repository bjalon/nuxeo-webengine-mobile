function $(id) {
  return document.getElementById(id);
}

window.onload = function() {
  $("username").value = localStorage.username;

  $("username").addEventListener("blur", function(event) {
    var username = $("username").value;
    localStorage.username = username;
  });

};

