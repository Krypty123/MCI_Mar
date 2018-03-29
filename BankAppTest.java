package org.cap.bankapptest;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InvalidInitialAmountException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAppTest {
	
	
	//Proxy object
	@Mock
	private AccountDao accountDao;
	
	//private AcccountService accountService=new AccountServiceImpl();
	private AcccountService accountService;
	
	@Before
	public void setUp(){
		//All proxy will be intialized
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountDao);
	}
	

	@Test
	public void test_addNumber_Method() {
		assertEquals(60, accountService.addNumbers(10, 50));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_addAccount_with_null_customer() throws InvalidInitialAmountException{
		
		Customer customer=null;
		accountService.addAccount(customer, 1300);
	}
	
	@Test(expected=InvalidInitialAmountException.class)
	public void test_addAccount_with_insufficient_balance() throws InvalidInitialAmountException{
		Customer customer=new Customer();
		customer.setCustName("Tom");
		Address address=new Address();
		address.setAddressLine("23 North Avvenue");
		customer.setCustAddress(address);
		
		accountService.addAccount(customer, 200);
		
	}
	
	
	
	
	
	@Test
	public void addAccount_with_mockito_Dao() throws InvalidInitialAmountException{
		Customer customer=new Customer();
		customer.setCustName("Tom");
		Address address=new Address();
		address.setAddressLine("23 North Avvenue");
		customer.setCustAddress(address);
		
		Account account=new Account();
		account.setCustomer(customer);
		account.setAmount(3000);
		//account.setAccountNo(accountNo);
		
		//dummy declation with Mocito
		Mockito.when(accountDao.createAccount(account)).thenReturn(true);
		
		//Actuall Logic exist in Service Tier
		Account newAccount=accountService.addAccount(customer, 3000);
		
		//Mockito verify
		Mockito.verify(accountDao).createAccount(account);
		
		//Manual Verification
		assertEquals(newAccount.getAmount(), account.getAmount(),0.0);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}














