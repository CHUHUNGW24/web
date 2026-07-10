package checkscam.service;
import java.util.ArrayList;
import java.util.List;
import checkscam.entity.Scammer;
import checkscam.exception.ScammerNotFoundException;
import checkscam.repository.Repository;
public class ScamService {
    private final Repository repository = new Repository();
    public Scammer checkScam(String input) throws ScammerNotFoundException, IllegalArgumentException {
        if (input == null || !input.matches("\\d{9,15}")) {
            throw new IllegalArgumentException("❌ Lỗi định dạng: Thông tin nhập vào phải là SỐ và có độ dài từ 9 - 15 ký tự!");
        }
        for (Scammer s : repository.findAll()) {
            if (s.getTarget().equals(input)) {
                return s;
            }
        }
        throw new ScammerNotFoundException("✅ AN TÂM: Số này hiện chưa có lịch sử lừa đảo báo cáo trên hệ thống.");
    }
    // 2. MỚI: Tính năng tìm kiếm gần đúng theo 3-4 số cuối
    public List<Scammer> searchByTrailingNumbers(String suffix) {
        List<Scammer> resultList = new ArrayList<>();
        if (suffix == null || suffix.trim().isEmpty()) {
            return resultList;
        }
        for (Scammer s : repository.findAll()) {
            // Kiểm tra xem số tài khoản/SĐT có kết thúc bằng chuỗi nhập vào hay không
            if (s.getTarget().endsWith(suffix.trim())) {
                resultList.add(s);
            }
        }
        return resultList;
    }
    // 3. MỚI: Tính năng tìm kiếm theo từ khóa hành vi lừa đảo (Không phân biệt hoa thường)
    public List<Scammer> searchByTypeKeyword(String keyword) {
        List<Scammer> resultList = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return resultList;
        }
        String lowerKeyword = keyword.trim().toLowerCase();
        for (Scammer s : repository.findAll()) {
            // Kiểm tra xem trường 'type' (hành vi) có chứa từ khóa hay không
            if (s.getType().toLowerCase().contains(lowerKeyword)) {
                resultList.add(s);
            }
        }
        return resultList;
    }
    public void addScammer(Scammer newScammer) {
        repository.save(newScammer);
        System.out.println("📥 Hệ thống: Đã nhận và lưu trữ bài báo cáo của bạn thành công!");
    }
    public List<Scammer> getAllScammers() {
        return repository.findAll();
    }
}