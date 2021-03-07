package in.hkb.samples.quarkus;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

@ApplicationScoped
public class EmployeeDAO {
	
	@Inject
	@DataSource("mysql")
	AgroalDataSource mysqlDataSource;
	
	private Jdbi jdbi = null;
	
	@PostConstruct
	public void initialize()
	{
		jdbi = Jdbi.create(mysqlDataSource);
		jdbi.installPlugin(new SqlObjectPlugin());
		jdbi.registerRowMapper(ConstructorMapper.factory(Employee.class));
	}

	public Employee createNewEmployee(Employee employee) throws Exception 
	{
		return jdbi.withExtension(EmployeeRepository.class, repository-> {
				employee.setId(repository.createNewEmployee(employee));
				return employee;
		});
	}
	
}
