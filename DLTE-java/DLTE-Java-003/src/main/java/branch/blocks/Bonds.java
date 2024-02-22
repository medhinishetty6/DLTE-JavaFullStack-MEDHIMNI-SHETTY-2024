package branch.blocks;

public class Bonds {
    public Bonds(Integer maturityAmount, Double interestrate, String taxstatus, String bondholder,Integer tenure) {
        this.maturityAmount = maturityAmount;
        this.interestrate = interestrate;
        this.taxstatus = taxstatus;
        this.bondholder = bondholder;
        this.tenuer=tenuer;
    }
    private Integer maturityAmount;
    private Double interestrate;
    private String taxstatus;
    private String bondholder;

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Integer maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Double getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(Double interestrate) {
        this.interestrate = interestrate;
    }

    public String getTaxstatus() {
        return taxstatus;
    }

    public void setTaxstatus(String taxstatus) {
        this.taxstatus = taxstatus;
    }

    public String getBondholder() {
        return bondholder;
    }

    public void setBondholder(String bondholder) {
        this.bondholder = bondholder;
    }


    public Integer getTenuer() {
        return tenuer;
    }

    public void setTenuer(Integer tenuer) {
        this.tenuer = tenuer;
    }
    private Integer tenuer;

    }



