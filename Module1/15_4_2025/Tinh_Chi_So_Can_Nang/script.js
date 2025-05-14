function tinhBMI() {
  let can_nang = +document.getElementById("weight").value;
  let chieu_cao = +document.getElementById("height").value;
  let BMI = can_nang / (chieu_cao * chieu_cao);
  if (BMI < 18.5) {
    document.getElementById("result").innerHTML = "Underweight";
  } else if (BMI < 25) {
    document.getElementById("result").innerHTML = "Normal";
  } else if (BMI < 30) {
    document.getElementById("result").innerHTML = "Overweight";
  } else {
    document.getElementById("result").innerHTML = "Obese";
  }
}
