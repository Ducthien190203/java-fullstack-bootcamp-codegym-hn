```
BEGIN
    INPUT diemKiemTra, diemGiuaKy, diemCuoiKy
    diemTrungBinh = (diemKiemTra + diemGiuaKy + diemCuoiKy) / 3

    IF diemTrungBinh >= 8 THEN
        OUTPUT "Học lực: Giỏi"
    ELSE IF diemTrungBinh >= 6.5 THEN
        OUTPUT "Học lực: Khá"
    ELSE IF diemTrungBinh >= 5 THEN
        OUTPUT "Học lực: Trung bình"
    ELSE
        OUTPUT "Học lực: Yếu"
END
```
