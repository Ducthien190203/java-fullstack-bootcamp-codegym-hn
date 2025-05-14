let diem_kiem_tra = +prompt("Nhập điểm bài kiểm tra");
let diem_giua_ky = +prompt("Nhập điểm thi giữa kỳ");
let diem_cuoi_ky = +prompt("Nhập điểm thi cuối kỳ");
let diem_trung_binh = (diem_kiem_tra + diem_giua_ky + diem_cuoi_ky) / 3;
 
if (diem_trung_binh >= 8) {
  alert("Học lực: Giỏi");
} else if (diem_trung_binh >= 6.5) {
  alert("Học lực: Khá");
} else if (diem_trung_binh >= 5) {
  alert("Học lực: Trung bình");
} else {
  alert("Học lực: Yếu");
}
