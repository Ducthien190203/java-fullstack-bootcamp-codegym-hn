function tinhToan() {
    const a = parseFloat(document.getElementById("a").value);
    const b = parseFloat(document.getElementById("b").value);
  
    // if (isNaN(a) || isNaN(b)) {
    //   document.getElementById("ketQua").innerText = "Vui lòng nhập hai số hợp lệ.";
    //   return;
    // }
  
    const tong = a + b;
    const tich = a * b;
  
    document.getElementById("ketQua").innerText =
      `Tổng: ${tong}\nTích: ${tich}`;
  }
  