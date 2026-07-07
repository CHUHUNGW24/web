package checkscam.repository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import checkscam.entity.Scammer;
import checkscam.entity.BankScammer;
public class Repository {
    private static final String FILE_PATH = "scammers.txt";
    private final List<Scammer> scammerList = new ArrayList<>();
    public Repository() {
        loadDataFromFile();
    }
    public List<Scammer> findAll() {
        return scammerList;
    }
    public void save(Scammer newScammer) {
        scammerList.add(newScammer);
        saveDataToFile(newScammer);
    }
    /**
     * 📥 1. HÀM ĐỌC FILE (Đã cấu trúc lại để tương thích ngược dữ liệu cũ, chống sập)
     */
    private void loadDataFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("🌱 Hệ thống: Tạo mới file dữ liệu kho lưu trữ 'scammers.txt'...");
            initSampleData();
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    long id = Long.parseLong(parts[0]);
                    String target = parts[1];
                    String type = parts[2];
                    String evidence = parts[3];
                    scammerList.add(new Scammer(id, target, type, evidence));
                    continue;
                }
                if (parts.length >= 5) {
                    String category = parts[0];
                    long id = Long.parseLong(parts[1]);
                    String target = parts[2];
                    String type = parts[3];
                    String evidence = parts[4];
                    if (category.equals("BANK") && parts.length == 7) {
                        String bankName = parts[5];
                        String accountHolder = parts[6];
                        scammerList.add(new BankScammer(id, target, type, evidence, bankName, accountHolder));
                    } else if (category.equals("NORMAL")) {
                        scammerList.add(new Scammer(id, target, type, evidence));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Lỗi khi đọc dữ liệu từ file: " + e.getMessage());
        }
    }
    /**
     * 📤 2. HÀM GHI FILE (Lưu chuẩn định dạng mới phân loại rõ ràng)
     */
    private void saveDataToFile(Scammer scammer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String dataLine;
            if (scammer instanceof BankScammer) {
                BankScammer bs = (BankScammer) scammer;
                dataLine = "BANK|" + bs.getId() + "|" + bs.getTarget() + "|" + bs.getType() + "|" +
                        bs.getEvidence() + "|" + bs.getBankName() + "|" + bs.getAccountHolder();
            } else {
                dataLine = "NORMAL|" + scammer.getId() + "|" + scammer.getTarget() + "|" +
                        scammer.getType() + "|" + scammer.getEvidence();
            }
            bw.write(dataLine + System.lineSeparator());
            bw.flush();
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi dữ liệu vào file: " + e.getMessage());
        }
    }
    /**
     * 🛠️ 3. HÀM KHỞI TẠO DỮ LIỆU MẪU BAN ĐẦU
     */
    private void initSampleData() {
        save(new BankScammer(1L, "190333444555", "Lừa đảo việc làm online", "Yêu cầu nạp tiền làm nhiệm vụ", "Techcombank", "Nguyen Van Gian"));
        save(new Scammer(2L, "9876543210", "Giả danh Công an / Viện kiểm sát", "Gọi điện hù dọa dính án ma túy, yêu cầu chuyển tiền"));
    }
}