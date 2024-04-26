import com.fasterxml.jackson.databind.*;
import org.example.model.*;
import org.example.repository.*;
import org.example.service.*;
import org.example.serviceImp.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.test.web.servlet.setup.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTests {



    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    @Mock
    EmployeeServiceImpl employeeService ;

    @BeforeEach
    public void setUp() {
       this.employeeService = new EmployeeServiceImpl(this.employeeRepository);
    }
    @Test
    void saveEmployeeTest() throws Exception {
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.saveEmployee(employee);
        Assertions.assertEquals(employee.getFirstName(),result.getFirstName());
    }

    @Test
    void saveEmployeeTestFailed() throws Exception {
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        Assertions.assertThrows(Exception.class, () -> employeeService.saveEmployee(employee),"Employee already exist with given email" );
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("Ramesh").lastName("Fadatare").email("ramesh@gmail.com").build());
        listOfEmployees.add(Employee.builder().firstName("Tony").lastName("Stark").email("tony@gmail.com").build());
        when(employeeRepository.findAll()).thenReturn(listOfEmployees);
        Assertions.assertEquals(2,employeeService.getAllEmployees().size());
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Assertions.assertTrue(employeeService.getEmployeeById(employeeId).isPresent());
    }


}
