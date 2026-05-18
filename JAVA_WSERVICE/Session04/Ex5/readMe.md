Phần 1 – Thiết kế API Spec

1. Lấy danh sách tất cả chuyến xe

HTTP Method

GET

Endpoint URL : /api/v1/trips

Mô tả

Lấy danh sách tất cả chuyến xe hiện có trong hệ thống.

Request Params

Có thể hỗ trợ thêm: ?page=1&size=10


Response
```json
[
  {
    "tripId": "T01",
    "from": "Ha Noi",
    "to": "TP.HCM",
    "departureTime": "2026-05-20T08:00:00",
    "availableSeats": 12
  }
]
 ```


2. Lấy thông tin chi tiết một chuyến xe

HTTP Method

GET

Endpoint URL : /api/v1/trips/{tripId}

Mô tả

Lấy thông tin chi tiết của một chuyến xe theo ID.

Request Params: tripId (Path Variable)

Response:

```json

{
  "tripId": "T01",
  "from": "Ha Noi",
  "to": "TP.HCM",
  "departureTime": "2026-05-20T08:00:00",
  "price": 850000,
  "availableSeats": 12
}
```
3. Tìm chuyến xe theo tuyến đường

HTTP Method

GET

Endpoint URL: /api/v1/trips/search?from=HaNoi&to=HCM


Mô tả

Tìm các chuyến xe theo điểm đi và điểm đến.

Request Params : from & to

Response: 

```json

[
  {
    "tripId": "T05",
    "from": "Ha Noi",
    "to": "TP.HCM",
    "departureTime": "2026-05-21T21:00:00"
  }
]
```
4. Đặt vé mới

HTTP Method

POST

Endpoint URL : /api/v1/bookings

Mô tả

Tạo mới một vé đặt xe cho hành khách.

Request Body

```json 
{
  "tripId": "T01",
  "passengerName": "Nguyen Van A",
  "phone": "0988888888",
  "seatNumber": 12
}
```


Response:


```json


{
  "bookingId": "B01",
  "status": "BOOKED",
  "message": "Booking created successfully"
}
```

5. Hủy vé đã đặt

HTTP Method

DELETE

Endpoint URL: /api/v1/bookings/{bookingId}


Mô tả

Hủy một vé đã đặt theo mã booking.

Request Params: bookingId (Path Variable)

Response:
```json

{
  "message": "Booking cancelled successfully"
}
```


6. Cập nhật thông tin hành khách trên vé

HTTP Method

PUT

Endpoint URl: /api/v1/bookings/{bookingId}/passenger
Request Body
```json

{
  "passengerName": "Tran Van B",
  "phone": "0977777777"
}
```
Response
```json

{
  "bookingId": "B01",
  "passengerName": "Tran Van B",
  "phone": "0977777777",
  "status": "BOOKED"
}
```

Phần 2 – Giải thích quyết định thiết kế

1. Vì sao dùng GET cho lấy danh sách chuyến xe?

```http request
GET /api/v1/trips
```


GET được dùng để lấy dữ liệu từ server mà không làm thay đổi dữ liệu. Việc lấy danh sách chuyến xe chỉ là thao tác đọc nên GET là phù hợp nhất.

2. Vì sao dùng GET cho lấy chi tiết chuyến xe?
```Http
GET /api/v1/trips/{tripId}
```

API này cũng chỉ dùng để đọc thông tin của một tài nguyên cụ thể (trip), nên GET là chuẩn RESTful.


3. Vì sao dùng GET cho tìm kiếm chuyến xe?
```http request
GET /api/v1/trips/search
```
   Tìm kiếm không tạo mới hay chỉnh sửa dữ liệu nên vẫn là thao tác đọc dữ liệu.


Dùng query parameter:
```http 
?from=HaNoi&to=HCM
```
giúp API dễ mở rộng thêm điều kiện lọc như:

* ngày đi
* giá vé
* loại xe

4. Vì sao dùng POST cho đặt vé?
```http request
POST /api/v1/bookings
```
Đặt vé tạo ra một tài nguyên mới là booking.

POST phù hợp vì:

* server sinh bookingId
* tạo dữ liệu mới trong database
* mỗi lần gọi có thể tạo dữ liệu khác nhau


5. Vì sao dùng DELETE cho hủy vé?
```http request

DELETE /api/v1/bookings/{bookingId}
```
Hủy vé là thao tác xóa tài nguyên booking khỏi hệ thống hoặc chuyển sang trạng thái canceled.

DELETE thể hiện đúng ý nghĩa REST:

* xóa tài nguyên
* thao tác theo ID cụ thể

⸻

6. Vì sao dùng PUT cho cập nhật thông tin hành khách?
```http request

PUT /api/v1/bookings/{bookingId}/passenger
```

PUT phù hợp khi cập nhật thông tin của một tài nguyên đã tồn tại.

Trong trường hợp này:

* cập nhật họ tên
* cập nhật số điện thoại

là thao tác update dữ liệu.



Endpoint phân vân giữa PUT và PATCH

Endpoint cập nhật hành khách là endpoint dễ phân vân nhất.

Có thể dùng:
```http request
PATCH /api/v1/bookings/{bookingId}/passenger
```


vì chỉ cập nhật một phần dữ liệu (tên, số điện thoại).

Tuy nhiên ở đây t chọn PUT vì:

* nghiệp vụ yêu cầu cập nhật toàn bộ thông tin hành khách
* request body gửi đầy đủ dữ liệu passenger
* dễ hiểu với người mới học REST API


