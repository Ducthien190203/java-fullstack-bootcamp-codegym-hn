// Mảng chứa danh sách sản phẩm
let products = [];

// Hàm hiển thị danh sách sản phẩm
function displayProducts() {
  const productList = document.getElementById("productList");
  productList.innerHTML = ""; // Xóa nội dung cũ

  products.forEach((product, index) => {
    const row = document.createElement("tr");

    const cell1 = document.createElement("td");
    cell1.textContent = index + 1;

    const cell2 = document.createElement("td");
    cell2.textContent = product;

    const cell3 = document.createElement("td");
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Xóa";
    deleteButton.onclick = () => deleteProduct(index);
    cell3.appendChild(deleteButton);

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    productList.appendChild(row);
  });
}

// Hàm thêm sản phẩm
function addProduct() {
  const newProduct = document.getElementById("newProduct").value;
  if (newProduct) {
    products.push(newProduct);
    displayProducts();
    document.getElementById("newProduct").value = ""; // Clear input
  } else {
    alert("Vui lòng nhập tên sản phẩm!");
  }
}

// Hàm sửa tên sản phẩm
function editProduct(index) {
  const newName = prompt("Nhập tên sản phẩm mới:", products[index]);
  if (newName) {
    products[index] = newName;
    displayProducts();
  }
}

// Hàm xóa sản phẩm
function deleteProduct(index) {
  products.splice(index, 1);
  displayProducts();
}

// Gọi hàm hiển thị khi trang load
displayProducts();
