package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction signIn(Transaction transaction) {
        int details = jdbcTemplate.update("insert into securitytransaction values(?,?,?,?,?)", new Object[]{
                transaction.getUsername(),
                transaction.getPassword(),
                transaction.getRole(),
                transaction.getAddress(),
                transaction.getContact(),
                transaction.getEmail()
        });
        return transaction;
    }
    public Transaction findByUsername(String username){
        Transaction transaction=jdbcTemplate.queryForObject("select*from securitytransaction where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(Transaction.class));
        return transaction;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Transaction officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
