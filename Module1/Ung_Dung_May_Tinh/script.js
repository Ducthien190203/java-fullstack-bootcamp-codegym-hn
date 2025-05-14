function cong() {
  let a = +document.getElementById("first_number").value;
  let b = +document.getElementById("second_number").value;
  let c = a + b;
  document.getElementById("Result").innerHTML = "Addition: " + c;
}
function tru() {
  let a = +document.getElementById("first_number").value;
  let b = +document.getElementById("second_number").value;
  let c = a - b;
  document.getElementById("Result").innerHTML = "Addition: " + c;
}
function nhan() {
  let a = +document.getElementById("first_number").value;
  let b = +document.getElementById("second_number").value;
  let c = a * b;
  document.getElementById("Result").innerHTML = "Addition: " + c;
}
function chia() {
    let a = +document.getElementById("first_number").value;
    let b = +document.getElementById("second_number").value;
  if (b === 0) {
    alert("Phép chia a/b thì b phải khác 0! Nhập lại số b");
  } else {
    
    let c = a / b;
    document.getElementById("Result").innerHTML = "Addition: " + c;
  }
}
