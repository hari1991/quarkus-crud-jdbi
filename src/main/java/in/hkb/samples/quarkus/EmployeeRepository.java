package in.hkb.samples.quarkus;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface EmployeeRepository {
	
	@SqlUpdate("INSERT INTO `test`.`employee` (`name`, `address`, `company`, `salary`) VALUES (:name, :address, :company, :salary)")
	@RegisterBeanMapper(value = Employee.class)
	@GetGeneratedKeys
	int createNewEmployee(@BindBean Employee employee);
	
}
