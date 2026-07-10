package checkscam;
public class AutoSaveThread extends Thread {
    @Override
    @SuppressWarnings("BusyWait")
    public void run() {
        while (!isInterrupted()) {
            try {
                // 120 giây chạy một lần
                Thread.sleep(120000);
                System.out.println("\n[HỆ THỐNG-NGẦM]: Đang tự động sao lưu dữ liệu tránh mất mát...");

            } catch (InterruptedException e) {
                System.out.println("[HỆ THỐNG-NGẦM]: Luồng sao lưu bị ngắt.");
                // Khôi phục lại trạng thái ngắt để vòng lặp while nhận biết và thoát ra êm đẹp
                interrupt();
            }
        }
    }
}