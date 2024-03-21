package spring.jpa.demo;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account")
public class AccountInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "account")
    @SequenceGenerator(name = "account",sequenceName = "transaction_seq",allocationSize = 1)
    private Long accountNumber;
    private String name;
    private String type;
    private String password;
    private String email;
    private Long phone;

    public AccountInformation() {
    }

    @Override
    public String toString() {
        return "AccountInformation{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }



}
