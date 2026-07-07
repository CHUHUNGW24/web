package checkscam.service;

import java.util.List;
import checkscam.entity.Scammer;
import checkscam.exception.ScammerNotFoundException;
import checkscam.repository.Repository;

public class ScamService {

    private Repository repository = new Repository();

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

    public void addScammer(Scammer newScammer) {

        repository.save(newScammer);
        System.out.println("📥 Hệ thống: Đã nhận và lưu trữ bài báo cáo của bạn thành công!");
    }

    public List<Scammer> getAllScammers() {
        return repository.findAll();
    }
}