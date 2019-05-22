package sg.edu.nus.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sg.edu.nus.demo.exception.UsernameNotFoundException;
import sg.edu.nus.demo.model.Administrator;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.AdministratorRepository;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.ManagerRepository;

@Service
public class LoginService {
	
	@Resource
	private EmployeeRepository employeeRepository;
	
	@Resource
	private ManagerRepository managerRepository;
	
	@Resource
	private AdministratorRepository administratorRepository;
	
	public boolean authenticateEmployee(String username, String password) {
		
		Employee employee = employeeRepository.findEmployeeByUserId(username);
		boolean authenticated = false;
		try {
			// Employee found in database
			if(employee != null) {
				// Hashed password using MD5
				String hashedPw = hashPassword(password);
				//compare password with employee password in database, return true if same, false if different
				if(hashedPw.compareTo(employee.getPassword()) == 0) {	
					authenticated = true;
				}
			}
			else {
				// Employee not found in database
				throw new UsernameNotFoundException();
			}
		}
		catch (UsernameNotFoundException e) {				
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return authenticated;
	}
	
	public boolean authenticateManager(String username, String password) {
		
		Manager manager = managerRepository.findManagerByUserId(username);
		boolean authenticated = false;
		try {
			// Manager found in database
			if(manager != null) {
				// Hashed password using MD5
				String hashedPw = hashPassword(password);
				//compare password with manager password in database, return true if same, false if different
				if(hashedPw.compareTo(manager.getPassword()) == 0) {	
					authenticated = true;
				}
			}
			else {
				// Manager not found in database
				throw new UsernameNotFoundException();
			}
		}
		catch (UsernameNotFoundException e) {				
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return authenticated;		
	}
	
	public boolean authenticateAdministrator(String username, String password) {
		
		Administrator administrator = administratorRepository.findAdministratorByUserId(username);
		boolean authenticated = false;
		try {
			// Administrator found in database
			if(administrator != null) {
				// Hashed password using MD5
				String hashedPw = hashPassword(password);
				//compare password with employee password in database, return true if same, false if different
				if(hashedPw.compareTo(administrator.getPassword()) == 0) {	
					authenticated = true;
				}
			}
			else {
				// Administrator not found in database
				throw new UsernameNotFoundException();
			}
		}
		catch (UsernameNotFoundException e) {				
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return authenticated;
	}
	
	public String hashPassword(String original) {
		
		String generatedPassword = null;
		try {
	        // Create MessageDigest instance for MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(original.getBytes());
	        //Get the hash's bytes
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        //Get complete hashed password in hex format
	        generatedPassword = sb.toString();
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return generatedPassword;
	}
}
