package checkscam.repository; 

import java.util.ArrayList;   
import java.util.List;        
import checkscam.entity.Scammer; 

public class Repository { 


    private final List<Scammer> scammerList = new ArrayList<>(); 

    public Repository() { 

        scammerList.add(new Scammer(1L, "0123456789", "Lừa đảo trúng thưởng qua mạng", "Giả danh tổng đài Shopee gửi link trúng thưởng")); 
        scammerList.add(new Scammer(2L, "0987654321", "Giả danh Công an / Viện kiểm sát", "Gọi điện hù dọa dính án ma túy, yêu cầu chuyển tiền")); 
        scammerList.add(new Scammer(3L, "190333444555", "Lừa đảo việc làm online", "Tuyển cộng tác viên làm nhiệm vụ Shopee/Tiki nhận hoa hồng cao rồi giật tiền"));
    } 

    public List<Scammer> findAll() { 
        return scammerList; 
    }

    public void save(Scammer newScammer) { 
        scammerList.add(newScammer); 
    }
}