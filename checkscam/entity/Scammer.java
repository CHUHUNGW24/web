package checkscam.entity;

public class Scammer {
    private Long id;
    private String target;
    private String type;
    private String evidence;


    public Scammer(Long id, String target, String type, String evidence) {
        this.id = id;
        this.target = target;
        this.type = type;
        this.evidence = evidence;
    }


    public String getTarget() {
        return target;
    }


    public Long getId() { return id; }
    public String getType() { return type; }
    public String getEvidence() { return evidence; }

    @Override
    public String toString() {
        return "🚨 ĐỐI TƯỢNG BỊ TỐ CÁO:\n" +
                "- Số TK/SĐT: " + target + "\n" +
                "- Hành vi: " + type + "\n" +
                "- Bằng chứng: " + evidence;
    }
}