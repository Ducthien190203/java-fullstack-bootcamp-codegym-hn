function result() {
  let Amount = +document.getElementById("Amount").value;
  if (Amount <= 0) {
    alert("Số tiền phải lớn hơn 0. Hãy nhập lại!");
  } else {
    let From_Currency = document.getElementById("From_Currency").value;
    let To_Currency = document.getElementById("To_Currency").value;
    if (From_Currency == "VietNam") {
      let USD = 25370,
        EUR = 27490,
        KRW = 17.56;
      if (To_Currency === "USD") {
        let newMoney = Amount / USD;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " USD";
      }
      if (To_Currency === "EUR") {
        let newMoney = Amount / EUR;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " EUR";
      }
      if (To_Currency === "KRW") {
        let newMoney = Amount / KRW;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " KRW";
      }
    }

    if (From_Currency == "China") {
      let USD = 7.058,
        EUR = 7.9,
        KRW = 201.13;
      if (To_Currency === "USD") {
        let newMoney = Amount / USD;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " USD";
      }
      if (To_Currency === "EUR") {
        let newMoney = Amount / EUR;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " EUR";
      }
      if (To_Currency === "KRW") {
        let newMoney = Amount / KRW;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " KRW";
      }
    }

    if (From_Currency == "Japan") {
      let USD = 144.5,
        EUR = 160.59,
        KRW = 0.49;
      if (To_Currency === "USD") {
        let newMoney = Amount / USD;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " USD";
      }
      if (To_Currency === "EUR") {
        let newMoney = Amount / EUR;
        document.getElementById("result").innerHTML =
          newMoney.toFixed(1) + " EUR";
      }
      if (To_Currency === "KRW") {
        let newMoney = Amount / KRW;
        document.getElementById("result").innerHTML =
          newMoney.toFixed() + " KRW";
      }
    }
  }
}
