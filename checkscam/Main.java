package checkscam;

import java.util.Scanner;
import java.util.List;
import checkscam.entity.Scammer;
import checkscam.service.ScamService;
import checkscam.exception.ScammerNotFoundException;

public class Main {
    public static void main(String[] args) {
        ScamService scamService = new ScamService();
        Scanner scanner = new Scanner(System.in);


        AutoSaveThread saveThread = new AutoSaveThread(); //
        saveThread.setDaemon(true); //
        saveThread.start();

        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("=========================================");
        System.out.println("   HỆ THỐNG QUẢN LÝ & TRA CỨU WEB CHECK SCAM   ");
        System.out.println("=========================================");

        while (true) { //
            System.out.println("\n--- MENU CHỨC NĂNG ---");
            System.out.println("1. Tra cứu Số tài khoản / Số điện thoại");
            System.out.println("2. Gửi báo cáo tố cáo kẻ lừa đảo mới");
            System.out.println("3. Xem danh sách tất cả kẻ lừa đảo");
            System.out.println("4. Thoát chương trình");
            System.out.print("Mời bạn chọn (1-4): ");

            if (!scanner.hasNextInt()) { //
                System.out.println("❌ Vui lòng chỉ nhập số từ 1 đến 4!"); //
                scanner.nextLine(); //
                continue; //
            } //

            int choice = scanner.nextInt(); //
            scanner.nextLine(); //

            if (choice == 1) { //
                System.out.print("➡️ Nhập Số tài khoản hoặc Số điện thoại cần kiểm tra: "); //
                String input = scanner.nextLine().trim(); //
                try { //
                    Scammer result = scamService.checkScam(input); //
                    System.out.println("\n-----------------------------------------"); //
                    System.out.println(result); //
                    System.out.println("-----------------------------------------"); //
                } catch (IllegalArgumentException | ScammerNotFoundException e) { //
                    System.out.println(e.getMessage()); //
                } //
            }
            else if (choice == 2) { //
                System.out.println("\n--- NHẬP THÔNG TIN TỐ CÁO ---"); //
                System.out.print("1. Nhập STK hoặc SĐT kẻ gian: "); //
                String target = scanner.nextLine().trim(); //

                System.out.print("2. Nhập hành vi lừa đảo: "); //
                String type = scanner.nextLine().trim(); //

                System.out.print("3. Nhập mô tả bằng chứng: "); //
                String evidence = scanner.nextLine().trim(); //

                long newId = scamService.getAllScammers().size() + 1; //

                Scammer newScammer = new Scammer(newId, target, type, evidence); //
                scamService.addScammer(newScammer); //
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
                System.out.println("👋 Cảm ơn bạn đã sử dụng hệ thống bảo mật Check Scam. Tạm biệt!"); //
                break; //
            }
            else { //
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn lại từ 1 đến 4."); //
            } //
        } //
        scanner.close(); //
    }
}