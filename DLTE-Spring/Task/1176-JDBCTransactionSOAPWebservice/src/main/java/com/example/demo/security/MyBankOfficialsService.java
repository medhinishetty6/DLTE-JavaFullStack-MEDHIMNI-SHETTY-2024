//package com.example.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyBankOfficialsService implements UserDetailsService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public MyBankOfficials signIn(MyBankOfficials myBankOfficials){
//        int details=jdbcTemplate.update("insert into mybankusers values(?,?,?,?,?,?)",new Object[]{
//            myBankOfficials.getUsername(),
//            myBankOfficials.getPassword(),
//            myBankOfficials.getRole(),
//            myBankOfficials.getAddress(),
//            myBankOfficials.getContact(),
//            myBankOfficials.getEmail()
//        });
//      return myBankOfficials;
//    }
//    public MyBankOfficials findByUsername(String username){
//        MyBankOfficials myBankOfficials=jdbcTemplate.queryForObject("select*from transaction where username=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
//        return myBankOfficials;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyBankOfficials officials = findByUsername(username);
//        if(officials==null)
//            throw new UsernameNotFoundException(username);
//        return officials;
//    }
//}
