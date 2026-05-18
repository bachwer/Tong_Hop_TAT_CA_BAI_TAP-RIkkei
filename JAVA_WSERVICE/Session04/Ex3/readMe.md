Phần 1 – Lý thuyết
So sánh @PathVariable và @RequestParam

* @PathVariable
    * Vị trí trên URL: nằm trực tiếp trong path URL.
    * Mục đích: dùng để định danh một tài nguyên cụ thể.
    * Tính bắt buộc: thường là bắt buộc vì phải có ID mới xác định được resource.
    * RESTful: rất phù hợp với thiết kế RESTful cho resource cụ thể.
    * Ví dụ:

/movies/M001

* @RequestParam
    * Vị trí trên URL: nằm sau dấu ? dưới dạng query parameter.
    * Mục đích: dùng để lọc, tìm kiếm, phân trang hoặc sắp xếp dữ liệu.
    * Tính bắt buộc: có thể optional.
    * RESTful: phù hợp cho query/filter dữ liệu.
    * Ví dụ:
/movies?genre=Sci-Fi

1. Khi nào dùng @PathVariable?

Dùng khi muốn truy cập một tài nguyên cụ thể.

Ví dụ:
```text
GET /api/v1/movies/M001
```

Ở đây:

* M001 là định danh duy nhất của Movie
* URL đang chỉ trực tiếp vào resource cụ thể

→ Dùng:

```java
@PathVariable
```

⸻

Ý nghĩa RESTful

Trong REST: /resource/{id}

là chuẩn để biểu diễn:

“Tài nguyên có ID cụ thể”

Ví dụ:

```text
GET /users/10
GET /orders/500
GET /movies/M001
```

2. Khi nào dùng @RequestParam?

Dùng cho:

* filter
* search
* sorting
* pagination

Ví dụ:

```text
GET /api/v1/movies?genre=Sci-Fi
```

Ở đây:

* genre=Sci-Fi chỉ là điều kiện lọc
* Không phải định danh duy nhất của resource

→ Dùng:
```java
GET /api/v1/movies?genre=Sci-Fi
```

Vì sao không nên hoán đổi? : GET /api/v1/movies/Sci-Fi
để filter theo genre.

Vì URL này ngụ ý:

“Movie có ID = Sci-Fi”

không phải:

“Danh sách movie thuộc thể loại Sci-Fi”

Cũng không nên dùng: GET /api/v1/movies?id=M001
để lấy chi tiết phim.

Dù chạy được, nhưng không RESTful bằng: GET /api/v1/movies/M001

Thiết kế RESTful chuẩn

Lấy chi tiết resource

GET /api/v1/movies/M001 → @RequestParam


