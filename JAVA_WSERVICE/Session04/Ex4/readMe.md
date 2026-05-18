Phần 1 – Phân tích lỗi trong khai báo của Junior Dev

Đoạn code:

```java

@PostMapping("/products/{productId}")
public Product updateProduct(@PathVariable String productId,
                             @RequestBody Product p) {
    ...
}

```

Có ít nhất 2 lỗi chính Tôi Đã tìm đc:



Lỗi 1: Dùng sai HTTP Method (@PostMapping)

Sai ở đâu?

API này dùng để cập nhật sản phẩm, nhưng lại sử dụng:

```java
@PostMapping

```

Vì sao sai?

Theo RESTful API:

* POST → tạo mới tài nguyên
* PUT → cập nhật toàn bộ tài nguyên đã tồn tại

Trong yêu cầu đề bài:

PUT /api/v1/products/{productId}

nên phải dùng: @PutMapping

Hậu quả

* Sai semantics REST API
* Frontend/client dễ gọi sai logic
* API documentation bị lệch chuẩn


Lỗi 2: Không xử lý trường hợp productId không tồn tại

Sai ở đâu?

Method chỉ nhận ID và Product nhưng không kiểm tra:

```text

productId có tồn tại trong database hay không

```
Vì sao sai?

Theo yêu cầu nghiệp vụ:

Nếu productId không tồn tại khi PUT hoặc DELETE → trả về thông báo lỗi phù hợp.

Nếu không kiểm tra:

* Có thể update nhầm dữ liệu
* Có thể tạo mới ngoài ý muốn
* API trả kết quả sai nghiệp vụ

Cách đúng

Phải kiểm tra trước:

```java
Optional<Product> existing = productService.findById(productId);

if(existing.isEmpty()) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Product not found");
}
```

Phần 2: kết Quả của T
Ví dụ Response

PUT thành công

{
    "id": "P01",
    "name": "Laptop Dell",
    "price": 25000000,
    "quantity": 10
}
PUT thất bại

{
"message": "Product with ID P99 not found"
}


DELETE thành công

{
"message": "Product with ID P01 deleted successfully"
}