package com.mindex.challenge;

import com.mindex.challenge.compensation.data.Compensation;
import com.mindex.challenge.employee.data.Employee;
import com.mindex.challenge.reportingStructure.controller.ReportingStructureController;
import com.mindex.challenge.reportingStructure.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ChallengeApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getReportingStructureForMultiLevelReports() {
		String employeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
		ResponseEntity<ReportingStructure> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/reportingStructure/" + employeeID, ReportingStructure.class, new HashMap<>());
		ReportingStructure rs = responseEntity.getBody();
		assertEquals(employeeID, rs.getEmployee().getEmployeeId());
		assertEquals(4, rs.getNumberOfReports());
	}

	@Test
	public void getReportingStructureForInvalidID() {
		String employeeID = "ffff";
		ResponseEntity<ReportingStructure> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/reportingStructure/" + employeeID, ReportingStructure.class, new HashMap<>());
		ReportingStructure rs = responseEntity.getBody();
		assertEquals(null, rs.getEmployee());
		assertEquals(0, rs.getNumberOfReports());
	}

	@Test
	public void createCompensation() {
		String employeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
		Compensation comp = new Compensation();
		comp.setEmployee(new Employee());
		comp.getEmployee().setEmployeeId(employeeID);
		comp.setSalary(12345);
		comp.setEffectiveDate(LocalDate.of(2023, 5, 14));

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Compensation> requestEntity = new HttpEntity<>(comp, headers);

		ResponseEntity<Compensation> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/compensation", requestEntity, Compensation.class, new HashMap<>());
		Compensation c = responseEntity.getBody();
		assertEquals(employeeID, c.getEmployee().getEmployeeId());
		assertEquals(12345, c.getSalary());
		assertEquals(LocalDate.of(2023, 5, 14), c.getEffectiveDate());
	}

	@Test
	public void getCompensation() {
		String employeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";

		//////////////PREPARE DATABASE FOR TEST///////////////
		{
			Compensation comp = new Compensation();
			comp.setEmployee(new Employee());
			comp.getEmployee().setEmployeeId(employeeID);
			comp.setSalary(12345);
			comp.setEffectiveDate(LocalDate.of(2023, 5, 14));

			HttpHeaders headers = new HttpHeaders();
			HttpEntity<Compensation> requestEntity = new HttpEntity<>(comp, headers);

			ResponseEntity<Compensation> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/compensation", requestEntity, Compensation.class, new HashMap<>());
		}
		//////////////////////////////////////////////////////

		ResponseEntity<Compensation[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/compensation/" + employeeID, Compensation[].class, new HashMap<>());
		Compensation[] c = responseEntity.getBody();
		assertEquals(1, c.length);
		assertEquals(employeeID, c[0].getEmployee().getEmployeeId());
		assertEquals(12345, c[0].getSalary());
		assertEquals(LocalDate.of(2023, 5, 14), c[0].getEffectiveDate());
	}

	@Test
	public void getCompensationInvalidEmployeeID() {
		String employeeID = "fffff";
		ResponseEntity<Compensation[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/compensation/" + employeeID, Compensation[].class, new HashMap<>());
		Compensation[] c = responseEntity.getBody();

		assertEquals(0, c.length);
	}
}
