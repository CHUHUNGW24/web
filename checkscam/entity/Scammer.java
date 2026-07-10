package checkscam.entity;
public class Scammer {
    private long id;
    private String target;
    private String type;
    private String evidence;
    public Scammer() {
    }
    public Scammer(long id, String target, String type, String evidence) {
        this.id = id;
        this.target = target;
        this.type = type;
        this.evidence = evidence;
    }
    public long getId() { return id; }
    public String getTarget() { return target; }
    public String getType() { return type; }
    public String getEvidence() { return evidence; }
    @Override
    public String toString() {
        return "[STT]: #" + id +
                "\n[ĐỐI TƯỢNG]: " + target +
                "\n[HÌNH THỨC]: " + type +
                "\n[BẰNG CHỨNG]: " + evidence;
    }
}