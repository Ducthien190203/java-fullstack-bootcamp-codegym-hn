let r = +prompt("Nhập bán kính");
const pi = 3.14;
if (isNaN(r) || r === 0) {
  alert("Phải nhập số và bán kính phải khác 0!");
} else {
  let C = 2 * pi * r;
  let S = pi * Math.pow(r, 2);
  alert(
    "Diện tích hình tròn S = " +
      S.toFixed(2) +
      "\nChu vi hình tròn C = " +
      C.toFixed(2)
  );
}
