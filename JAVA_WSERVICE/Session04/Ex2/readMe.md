
Phần 1 – Lý thuyết
So sánh POST và PUT\
* POST
    * Mục đích: dùng để tạo mới tài nguyên.
    * URI: server sẽ quyết định URI/ID mới cho resource.
    * Idempotent: không idempotent.
    * Khi gọi nhiều lần: có thể tạo ra nhiều dữ liệu mới khác nhau.
    * Thường dùng khi: create resource.
    * Ví dụ: POST /api/v1/orders

* PUT
    * Mục đích: dùng để tạo mới hoặc cập nhật tài nguyên tại một URI đã xác định.
    * URI: client biết trước URI của resource.
    * Idempotent: có idempotent.
    * Khi gọi nhiều lần: kết quả cuối cùng vẫn giống nhau.
    * Thường dùng khi: update/replace resource.
    * Ví dụ: PUT /api/v1/orders/ORD001




Idempotent là gì?

Một HTTP method được gọi là idempotent nếu:

Gửi cùng một request nhiều lần vẫn cho cùng một kết quả cuối cùng.

⸻

Ví dụ POST

```text
POST /api/v1/orders
```

{
    "customerName": "An",
    "productId": "P100"
}

Nếu gửi 2 lần:

* Tạo 2 order khác nhau
* Sinh 2 orderId khác nhau

→ Kết quả thay đổi mỗi lần gọi
→ POST không idempotent


Ví dụ PUT

```text
PUT /api/v1/orders/123
```
Gửi 10 lần vẫn chỉ cập nhật resource ORD001.

→ Kết quả cuối cùng giống nhau
→ PUT idempotent

Ai đúng?

Đồng nghiệp B đúng

Vì nghiệp vụ đang là:

* Tạo đơn hàng mới
* orderId do server tự sinh
* Client chưa biết ID trước

Đây là đặc trưng của:

```text
POST /api/v1/orders
```

Khi nào dùng PUT?

PUT phù hợp khi:

* Client biết trước ID resource
* Muốn update hoặc replace toàn bộ dữ liệu

Ví dụ:

```text
PUT /api/v1/orders/123
```


Ví dụ Request

HTTP Request

```text
POST /api/v1/orders

Content-Type: application/json
```



Request Body
{

"customerName": "Nguyen Van A",

"address": "Ha Noi",

"productId": "P1001",

"quantity": 2

}

Ví dụ Response

{
"orderId": "7b7e7d6f-2d8d-4e89-9f8c-c2e34a5f0d12",
"customerName": "Nguyen Van A",
"address": "Ha Noi",
"productId": "P1001",
"quantity": 2
}


Điểm quan trọng trong code

@PostMapping

Dùng cho HTTP POST.

⸻

@RequestBody

Spring Boot sẽ:

* Đọc JSON từ body request
* Convert → object Java

⸻

UUID.randomUUID()

Tự sinh ID duy nhất cho order:

```text
UUID.randomUUID().toString();
```

Kết luận

Đúng chuẩn RESTful:

POST /api/v1/orders


vì:

* Đang tạo tài nguyên mới
* ID do server sinh
* POST không idempotent phù hợp với create operation.

