package checkscam;
public class AutoSaveThread extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(60000); // 60 giây chạy một lần
                System.out.println("\n[HỆ THỐNG-NGẦM]: Đang tự động sao lưu dữ liệu tránh mất mát...");
            }
        } catch (InterruptedException e) {
            System.out.println("[HỆ THỐNG-NGẦM]: Luồng sao lưu bị ngắt.");
        }
    }
}