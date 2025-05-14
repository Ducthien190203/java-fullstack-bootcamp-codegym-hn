let vatLy = +prompt('Điểm Vật Lý');
let hoaHoc = +prompt('Điểm Hoá Học');
let sinhHoc = +prompt('Điểm Sinh Học');
let diemTong = vatLy+hoaHoc+sinhHoc;
let diemTB = diemTong/3;
alert('Điểm trung bình: '+diemTB.toFixed(1)+'\nTổng điểm: '+diemTong);
