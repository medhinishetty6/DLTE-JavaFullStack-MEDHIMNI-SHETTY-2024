package org.example;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.example.entity.Account;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;
import org.example.services.AccountServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.login.AccountNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private UserDetailsRepository mockUserDetailsRepository;
    private AccountServices services;
    @Before
    public void prepareStore(){
        when(mockStorageTarget.getUserDetailsRepository()).thenReturn(mockUserDetailsRepository);
        services=new AccountServices(mockStorageTarget);
    }

    @Test
    public void testForAutenticate(){
        Account account=new Account("Medhini","Medh@06","medh@gmail.com",8935667889L,9000.0);
        Account account1=new Account("Meghana","Megh@06","megh@gmail.com",9855677889L,90000.0);

        doNothing().when(mockUserDetailsRepository).authenticate(account);
        services.authenticate(account);
        verify(mockUserDetailsRepository).authenticate(account);

    }
    @Test
    public void testFindById() throws AccountNotFoundException {
        String username="Medhini";
        String username1="Meghana";

        Account account=new Account("Medhini","Medh@06","medh@gmail.com",8935667889L,9000.0);
        Account account1=new Account("Meghana","Megh@06","megh@gmail.com",9855677889L,90000.0);

        when(mockUserDetailsRepository.getAccountByUsername(username)).thenReturn(account);
        Account actualAccount = (Account) services.getAllAccounts(account);
        assertNotSame(account.getUsername(),actualAccount.getUsername());
    }

}
