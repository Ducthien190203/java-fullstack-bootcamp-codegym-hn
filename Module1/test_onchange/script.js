// function do_something(){
//     var selectedValue =document.getElementById('mySelect').value;
//     alert(selectedValue);
// }
var selectedElement = document.getElementById("mySelect");
selectedElement.addEventListener("change", changeFunction);
function changeFunction() {
  var selectValue = document.getElementById("mySelect").value;
  alert(selectValue);
}
