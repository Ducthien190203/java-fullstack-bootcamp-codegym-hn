let a = +prompt('Nhập một số nguyên');
if (isNaN(a)) {
  alert('Vui lòng nhập số nguyên')
} else if (a > 0) {
  alert(a+' > 0')
} else if (a === 0) {
  alert(a+' = 0')
}else{alert(a+ ' < 0')}