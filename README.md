# Hệ Thống Web Check Scam

* Hệ thống Check Scam là một ứng dụng dòng lệnh (Console) được phát triển bằng ngôn ngữ Java
* Mục đích hỗ trợ người dùng nhận diện, tra cứu và báo cáo các đối tượng có hành vi lừa đảo trực tuyến (qua số điện thoại, tài khoản mạng xã hội hoặc số tài khoản ngân hàng).
* Giúp người tiêu dùng giảm thiểu thiệt hại và phát hiện lừa đảo khi mua hàng online

---

## Tính Năng Chính

* **Tra cứu thông tin lừa đảo:** Cho phép kiểm tra nhanh một số điện thoại hoặc số tài khoản ngân hàng xem có lịch sử lừa đảo hay không. Hệ thống tự động xác thực định dạng đầu vào (chỉ chấp nhận số có độ dài từ 9 - 15 ký tự).
* **Gửi báo cáo tố cáo mới:** Người dùng có thể đóng góp dữ liệu bằng cách gửi thông tin tố cáo. Hệ thống phân loại thông minh giữa đối tượng thông thường (SĐT/STK) và đối tượng ngân hàng (yêu cầu thêm Tên ngân hàng, Chủ tài khoản).
* **Hiển thị danh sách đen:** Liệt kê toàn bộ danh sách các đối tượng lừa đảo đang được lưu trữ trên hệ thống kèm bằng chứng cụ thể.
* **Tự động sao lưu dữ liệu ngầm (Auto-Save):** Tích hợp một luồng ngầm (Daemon Thread) tự động chạy định kỳ mỗi 120 giây để thông báo bảo vệ và đồng bộ dữ liệu, tránh mất mát thông tin.

---

## Công Nghệ Sử Dụng

* **Ngôn ngữ chính:** Java Core / Java SE (Hỗ trợ Java 8 trở lên).
* **Kiến trúc phần mềm:** Phân tầng chuẩn (Layered Architecture) bao gồm các tầng:
  * `entity`: Định nghĩa đối tượng (`Scammer`, `BankScammer` áp dụng tính kế thừa).
  * `repository`: Quản lý việc đọc/ghi dữ liệu bền vững.
  * `service`: Xử lý logic nghiệp vụ, thuật toán tra cứu và kiểm tra lỗi.
  * `Main`: Giao diện dòng lệnh điều hướng menu tương tác.
* **Cơ sở dữ liệu:** Lưu trữ dạng File I/O phẳng (`scammers.txt`) tối ưu dung lượng.
* **Kỹ thuật nâng cao:** Đa luồng (Multithreading), Biểu thức chính quy (Regex), Xử lý ngoại lệ tùy chỉnh (Custom Exception).

---

## Cấu Trúc Thư Mục Dự Án

```text
checkscam/
├── entity/
│   ├── Scammer.java            # Lớp cơ sở chứa thông tin kẻ lừa đảo chung
│   └── BankScammer.java        # Lớp kế thừa chuyên biệt cho tài khoản ngân hàng
├── repository/
│   └── Repository.java         # Quản lý đọc/ghi cơ sở dữ liệu file 'scammers.txt'
├── service/
│   └── ScamService.java        # Xử lý thuật toán tra cứu và xác thực dữ liệu
├── exception/
│   └── ScammerNotFoundException.java # Ngoại lệ tùy chỉnh khi không tìm thấy đối tượng
├── AutoSaveThread.java         # Luồng chạy ngầm tự động sao lưu định kỳ
├── Main.java                   # Điểm khởi chạy ứng dụng và điều hướng Menu
└── scammers.txt                # File cơ sở dữ liệu lưu trữ (Tự động sinh ra khi chạy)