package checkscam.entity;
public class BankScammer extends Scammer {
    private String bankName;
    private String accountHolder;
    public BankScammer() {
        super();
    }
    public BankScammer(long id, String target, String type, String evidence, String bankName, String accountHolder) {
        super(id, target, type, evidence);
        this.bankName = bankName;
        this.accountHolder = accountHolder;
    }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }
    @Override
    public String toString() {
        return super.toString() +
                "\n[NGÂN HÀNG]: " + bankName +
                "\n[TÊN TÀI KHOẢN]: " + accountHolder.toUpperCase();
    }
}