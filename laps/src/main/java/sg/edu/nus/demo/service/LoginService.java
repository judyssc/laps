package sg.edu.nus.demo.service;

public interface LoginService {

	boolean authenticateEmployee(String username, String password);

	boolean authenticateManager(String username, String password);

	boolean authenticateAdministrator(String username, String password);

	String hashPassword(String original);

}