package in.hkb.samples.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EmployeeService {

	@Inject
	EmployeeDAO employeeDAO;
	
	public Employee addNewEmployee(Employee employee) {
		employee.setId(0);
		try {
			employee = employeeDAO.createNewEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	
	
}
