package checkscam;
import java.util.Scanner;
import java.util.List;
import checkscam.entity.Scammer;
import checkscam.entity.BankScammer;
import checkscam.service.ScamService;
import checkscam.exception.ScammerNotFoundException;
public class Main {
    public static void main(String[] args) {
        ScamService scamService = new ScamService();
        Scanner scanner = new Scanner(System.in);
        AutoSaveThread saveThread = new AutoSaveThread();
        saveThread.setDaemon(true);
        saveThread.start();
        try {
            // Sử dụng StandardCharsets.UTF_8 thay vì chuỗi "UTF-8" viết tay
            System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Thay thế printStackTrace() bằng System.err để ghi nhận lỗi robust hơn
            System.err.println("❌ Lỗi cấu trúc hiển thị ngôn ngữ: " + e.getMessage());
        }
        System.out.println("=========================================");
        System.out.println("   HỆ THỐNG QUẢN LÝ & TRA CỨU WEB CHECK SCAM   ");
        System.out.println("=========================================");
        while (true) {
            System.out.println("\n--- MENU CHỨC NĂNG ---");
            System.out.println("1. Tra cứu / Tìm kiếm kẻ lừa đảo (Nâng cấp)");
            System.out.println("2. Gửi báo cáo tố cáo kẻ lừa đảo mới");
            System.out.println("3. Xem danh sách tất cả kẻ lừa đảo");
            System.out.println("4. Thoát chương trình");
            System.out.print("Mời bạn chọn (1-4): ");
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Vui lòng chỉ nhập số từ 1 đến 4!");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                // MENU CON CHO TÍNH NĂNG TÌM KIẾM NÂNG CAO
                System.out.println("\n--- CHẾ ĐỘ TÌM KIẾM NÂNG CAO ---");
                System.out.println("1. Tra cứu chính xác (Yêu cầu đầy đủ SĐT/STK)");
                System.out.println("2. Tìm kiếm gần đúng (Theo 3-4 số cuối)");
                System.out.println("3. Tìm kiếm theo loại hành vi (Ví dụ: Shopee, Công an...)");
                System.out.print("Mời lựa chọn chế độ (1-3): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Lựa chọn không hợp lệ!");
                    scanner.nextLine();
                    continue;
                }
                int searchMode = scanner.nextInt();
                scanner.nextLine();
                if (searchMode == 1) {
                    // CHẾ ĐỘ 1: Tìm kiếm chính xác
                    System.out.print("➡️ Nhập chính xác Số tài khoản hoặc Số điện thoại: ");
                    String input = scanner.nextLine().trim();
                    try {
                        Scammer result = scamService.checkScam(input);
                        System.out.println("\n-----------------------------------------");
                        System.out.println(result);
                        System.out.println("-----------------------------------------");
                    } catch (IllegalArgumentException | ScammerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (searchMode == 2) {
                    // CHẾ ĐỘ 2: Tìm gần đúng theo đuôi số
                    System.out.print("➡️ Nhập các số cuối cần tìm kiếm (ví dụ '210' hoặc '6789'): ");
                    String suffix = scanner.nextLine().trim();
                    List<Scammer> results = scamService.searchByTrailingNumbers(suffix);
                    System.out.println("\n🔍 KẾT QUẢ TÌM KIẾM ĐUÔI SỐ LÀ: '" + suffix + "'");
                    if (results.isEmpty()) {
                        System.out.println("🌱 Không tìm thấy đối tượng nghi ngờ nào trùng đuôi số này.");
                    } else {
                        System.out.println("🚨 PHÁT HIỆN " + results.size() + " ĐỐI TƯỢNG TÌNH NGHI:");
                        for (Scammer s : results) {
                            System.out.println(s);
                            System.out.println("-----------------------------------------");
                        }
                    }
                }
                else if (searchMode == 3) {
                    // CHẾ ĐỘ 3: Tìm theo từ khóa hành vi
                    System.out.print("➡️ Nhập từ khóa hành vi cần lọc (Ví dụ: 'Shopee', 'trúng thưởng'): ");
                    String keyword = scanner.nextLine().trim();
                    List<Scammer> results = scamService.searchByTypeKeyword(keyword);
                    System.out.println("\n🔍 DANH SÁCH LỌC THEO TỪ KHÓA HÀNH VI: '" + keyword + "'");
                    if (results.isEmpty()) {
                        System.out.println("🌱 Không có hành vi lừa đảo nào trùng với từ khóa bạn tìm.");
                    } else {
                        System.out.println("🚨 TÌM THẤY " + results.size() + " HỒ SƠ LIÊN QUAN:");
                        for (Scammer s : results) {
                            System.out.println(s);
                            System.out.println("-----------------------------------------");
                        }
                    }
                }
                else {
                    System.out.println("❌ Lựa chọn chế độ không hợp lệ!");
                }
            }
            else if (choice == 2) {
                System.out.println("\n--- CHỌN LOẠI ĐỐI TƯỢNG TỐ CÁO ---");
                System.out.println("1. Tố cáo Số điện thoại / Tài khoản mạng xã hội");
                System.out.println("2. Tố cáo Số tài khoản ngân hàng lừa đảo");
                System.out.print("Mời bạn lựa chọn (1-2): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Vui lòng nhập số 1 hoặc 2!");
                    scanner.nextLine();
                    continue;
                }
                int typeChoice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n--- NHẬP THÔNG TIN TỐ CÁO ---");
                System.out.print("1. Nhập STK hoặc SĐT kẻ gian: ");
                String target = scanner.nextLine().trim();
                System.out.print("2. Nhập hành vi lừa đảo: ");
                String type = scanner.nextLine().trim();
                System.out.print("3. Nhập mô tả bằng chứng: ");
                String evidence = scanner.nextLine().trim();
                long newId = scamService.getAllScammers().size() + 1;
                Scammer newScammer;
                if (typeChoice == 2) {
                    System.out.print("4. Nhập Tên ngân hàng: ");
                    String bankName = scanner.nextLine().trim();
                    System.out.print("5. Nhập Tên chủ tài khoản ngân hàng: ");
                    String accountHolder = scanner.nextLine().trim();
                    newScammer = new BankScammer(newId, target, type, evidence, bankName, accountHolder);
                } else {
                    newScammer = new Scammer(newId, target, type, evidence);
                }
                scamService.addScammer(newScammer);
            }
            else if (choice == 3) {
                System.out.println("\n--- DANH SÁCH ĐỐI TƯỢNG LỪA ĐẢO TRÊN HỆ THỐNG ---");
                List<Scammer> list = scamService.getAllScammers();
                if (list.isEmpty()) {
                    System.out.println("🌱 Hệ thống hiện tại trống, chưa có dữ liệu báo cáo nào.");
                } else {
                    for (Scammer s : list) {
                        System.out.println(s);
                        System.out.println("-----------------------------------------");
                    }
                }
            }
            else if (choice == 4) {
                System.out.println("👋 Cảm ơn bạn đã sử dụng hệ thống bảo mật Check Scam. Tạm biệt!");
                break;
            }
            else {
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn lại từ 1 đến 4.");
            }
        }
        scanner.close();
    }
}