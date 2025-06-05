// Lớp MyList triển khai kiểu generic để có thể lưu trữ bất kỳ kiểu dữ liệu nào
public class MyList<E> {
    private int size = 0;          // Số phần tử hiện tại
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;     // Mảng chứa phần tử (dùng Object vì không tạo mảng generic được)

    // Constructor mặc định
    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // Constructor với kích thước ban đầu
    public MyList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative");
        }
        elements = new Object[capacity];
    }

    // Mở rộng mảng khi đầy
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];
            // Sao chép dữ liệu sang mảng mới
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    // Thêm phần tử vào cuối
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    // Thêm phần tử vào vị trí index
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity();
        // Dịch chuyển phần tử từ index sang phải 1 vị trí
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    // Lấy phần tử tại index
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    // Thay thế phần tử tại index
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    // Xóa phần tử tại index
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Giúp GC
        return removedElement;
    }

    // Trả về số lượng phần tử hiện tại
    public int size() {
        return size;
    }

    // Xóa tất cả phần tử
    public void clear() {
        // Xóa tham chiếu để GC
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    // Kiểm tra chỉ số hợp lệ khi get, set, remove
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: "+ index + ", Size: " + size);
        }
    }

    // Kiểm tra chỉ số hợp lệ khi add (index có thể bằng size)
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "+ index + ", Size: " + size);
        }
    }
}
