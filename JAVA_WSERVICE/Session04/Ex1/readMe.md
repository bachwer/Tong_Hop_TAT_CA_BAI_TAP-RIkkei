**Phần 1 – Phân tích logic**

-Nguyên nhân client nhận về chuỗi dạng:


```text
[Movie@3a1b2c3d, Movie@4f2e1a0b]
```

thay vì JSON là do đoạn code:

```java
return movies.toString();
```

Vì sao xảy ra lỗi?

* movies là một List<Movie>.
* Khi gọi toString() trên List, Java sẽ gọi tiếp toString() của từng object Movie.
* Nhưng class Movie không override phương thức toString().
  Do đó Java sử dụng toString() mặc định từ class Object, có format:

```text
TênClass@địa_chỉ_bộ_nhớ_hex

```

```text
Movie@3a1b2c3d
```

```text
nên toàn bộ response trở thành:
```

Đây chỉ là chuỗi text thông thường, không phải JSON hợp lệ, nên frontend không thể parse.

Gốc rễ vấn đề

Sai ở thiết kế API response:

```text
public String getMovies()

```

và:
```text

return movies.toString();
```


Trong Spring Boot, để framework tự động chuyển object thành JSON bằng Jackson, cần:

* Trả về List<Movie>
* Không ép sang String

Kết Quả
Khi Gọi: GET /api/v1/movies


[
  {
  "movieId": "M001",
  "title": "Inception",
  "genre": "Sci-Fi",
  "rating": 8.8
  },
  {
  "movieId": "M002",
  "title": "Parasite",
  "genre": "Drama",
  "rating": 8.6
  }
]
Giải thích thêm về Spring Boot

@RestController kết hợp với Jackson sẽ tự động:

* Convert object Java → JSON
* Set header:
  Content-Type: application/json

Điều kiện:

* Object phải có getter
* Không trả về String thủ công

⸻

Kết luận

Lỗi chính: return movies.toString();

Cáh Dùng  : public List<Movie> getMovies()
và: return movies;

để Spring Boot tự chuyển đổi sang JSON hợp lệ.


return movies;

