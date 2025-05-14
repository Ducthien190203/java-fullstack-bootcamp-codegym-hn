// let quaBong = null;
// quaBong = document.getElementById("myImage");
// function init() {
//   quaBong = document.getElementById("myImage");
//   quaBong.style.position = "relative";
//   quaBong.style.left = "0px";
// }
// function moveRight() {
//   quaBong.style.left = parseInt(quaBong.style.left) + 50 + "px";
// }
// window.onload = init;
let quaBong = document.getElementById("myImage");
function init() {
  quaBong = document.getElementById("myImage");
  quaBong.style.position = "relative";
  quaBong.style.left = "0px";
}
function moveRight() {
  quaBong.style.left = parseInt(quaBong.style.left) + 111 + "px";
}
window.onload = init;
