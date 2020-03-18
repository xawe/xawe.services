package com.xawe.micro.tests.cases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.xawe.micro.cases.EcNumber;

class EcNumberTest {

	EcNumber ec ;
	
	@BeforeEach
	void setUp() throws Exception {
		ec = new com.xawe.micro.cases.EcNumberImpl();
	}

	@Test
	@DisplayName("Test size of a created Ec")
	public void TestEcSize() {
		String result = ec.CreateEcNumber();
		System.out.println(result);
		assertEquals(result.length(), 8);
	}
	@Test
	@DisplayName("A massive ec size test 😱")
	public void MassiveEcSizeTest() {
		for (int i = 0; i < 200000; i++) {
			String r = ec.CreateEcNumber();
			System.out.println(r);
			assertEquals(8, r.length());
		}
	}
	
	
	@Test
	@DisplayName("Test if Ec is Null")
	public void TestNullEc() {
		assertNotNull(ec.CreateEcNumber());
	}

}
