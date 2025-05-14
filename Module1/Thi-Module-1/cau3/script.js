// Danh sách sách mẫu
let books = [
  { id: "12345", name: "Toán", year: 2000, quantity: 3, status: true },
  { id: "23456", name: "Văn", year: 1998, quantity: 4, status: true },
  {
    id: "37456",
    name: "Tiếng Anh",
    year: 1999,
    quantity: 5,
    status: true,
  },
];

function renderBooks() {
  const list = document.getElementById("bookList");
  list.innerHTML = "";
  books.forEach((book) => {
    list.innerHTML += `
          <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.year}</td>
            <td>${book.quantity}</td>
            <td>${book.status}</td>
          </tr>
        `;
  });
}

function addExistingBook() {
  const id = prompt("Nhập mã số sách đã có:");
  if (!/^[1-5][0-9]{4}$/.test(id)) {
    alert("Mã số sách không hợp lệ. Phải là chuỗi 5 ký tự, bắt đầu từ 1-5.");
    return;
  }

  const book = books.find((b) => b.id === id);
  if (book) {
    book.quantity++;
    book.status = true;
    alert("Đã tăng số quyển của sách.");
  } else {
    alert("Không tìm thấy sách.");
  }
  renderBooks();
}

function addNewBook() {
  const id = prompt("Nhập mã số sách (5 ký tự, bắt đầu từ 1-5):");
  if (!/^[1-5][0-9]{4}$/.test(id)) {
    alert("Mã số sách không hợp lệ. Phải là chuỗi 5 ký tự, bắt đầu từ 1-5.");
    return;
  }

  const isDuplicate = books.some((book) => book.id === id);
  if (isDuplicate) {
    alert("Mã số sách đã tồn tại. Vui lòng nhập mã khác.");
    return;
  }

  const name = prompt("Nhập tên sách:");
  if (!name || name.trim() === "") {
    alert("Tên sách không được để trống.");
    return;
  }

  const year = parseInt(prompt("Nhập năm xuất bản (4 chữ số):"));
  if (!/^\d{4}$/.test(year.toString()) || year < 1000 || year > 9999) {
    alert("Năm xuất bản không hợp lệ. Phải là số nguyên 4 chữ số.");
    return;
  }

  const quantity = parseInt(prompt("Nhập số quyển:"));
  if (isNaN(quantity) || quantity <= 0) {
    alert("Số quyển phải là số nguyên dương.");
    return;
  }

  books.push({ id, name, year, quantity, status: true });
  alert("Đã thêm sách mới.");
  renderBooks();
}

function borrowBook() {
  const id = prompt("Nhập mã số sách muốn mượn:");
  const book = books.find((b) => b.id === id);
  if (book && book.quantity > 0) {
    book.quantity--;
    if (book.quantity === 0) book.status = false;
    alert("Đã mượn sách.");
  } else {
    alert("Không thể mượn sách.");
  }
  renderBooks();
}

function showMostAvailableBook() {
  let maxBook = books.reduce(
    (max, book) => (book.quantity > max.quantity ? book : max),
    books[0]
  );
  alert(
    `Sách có nhiều quyển nhất: ${maxBook.name} : ${maxBook.quantity} quyển`
  );
}

renderBooks();
