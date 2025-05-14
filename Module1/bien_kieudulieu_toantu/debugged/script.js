let a = +prompt("Nhập số cân nặng");
if (isNaN(a)) {
  alert("Bạn phải nhập số");
} else if (a < 50) {
  alert("Bạn nên mặc size S");
} else if (a < 65) {
  alert("Bạn nên mặc size M");
} else if (a < 80) {
  alert("Bạn nên mặc size L");
} else {
  alert("Bạn nên mặc size XL");
}
