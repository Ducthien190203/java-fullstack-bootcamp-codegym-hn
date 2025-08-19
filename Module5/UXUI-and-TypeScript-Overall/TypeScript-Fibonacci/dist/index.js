"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// 1. Khai báo một hàm trả về số fibonacci bằng cách sử dụng đệ quy
function fibonacci(n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
// 2. Sử dụng vòng lặp để hiển thị các số fibonacci
const count = 10; // Hiển thị 10 số fibonacci đầu tiên
let fibonacciSeries = [];
console.log(`Dãy ${count} số Fibonacci đầu tiên:`);
for (let i = 0; i < count; i++) {
    const fibNumber = fibonacci(i);
    fibonacciSeries.push(fibNumber);
    console.log(fibNumber);
}
// 3. Khai báo biến sum = 0
let sum = 0;
// 4. Thực hiện việc tính tổng các số fibonacci
for (const num of fibonacciSeries) {
    sum += num;
}
console.log(`Tổng các số Fibonacci trong dãy trên là: ${sum}`);
//# sourceMappingURL=index.js.map