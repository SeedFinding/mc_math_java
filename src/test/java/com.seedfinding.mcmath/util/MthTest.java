package com.seedfinding.mcmath.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.seedfinding.mcmath.Benchmark;
import org.junit.jupiter.api.TestReporter;

@DisplayName("Measure speed of utils")
@Tag("Speed")
@Benchmark
public class MthTest {
	@BeforeEach
	public void setup(TestInfo info) {}

	@AfterEach
	public void tearDown(TestInfo info) {}

	public void run(Runnable runnable) {
		System.out.println("Warmup");
		for(int i = 0; i < 10000; i++) {
			runnable.run();
		}
		long time=System.nanoTime();
		for(int i = 0; i < 10000000; i++) {
			runnable.run();
		}
		System.out.printf("Took %d us\n",(System.nanoTime()-time)/1000);
	}

	@Test
	@Benchmark
	@DisplayName("Test Modular Inverse New")
	public void modInverseTest(TestInfo info) {
		run(modInverseRunnable(Mth::modInverse));
	}

	@Test
	@Benchmark
	@DisplayName("Test Modular Inverse Old")
	public void modInverseOldTest(TestInfo info) {
		run(modInverseRunnable(Mth::modInverseOld));
	}

	public Runnable modInverseRunnable(BiFunction<Long,Integer,Long> fn){
		return ()-> {
			assertEquals(fn.apply(11L, 64),3353953467947191203L,"Modular inverse of 11 mod 2^64 failed");
			assertEquals(fn.apply(11L, 32),3123612579L,"Modular inverse of 11 mod 2^32 failed");
			assertEquals(fn.apply(11L, 16),35747L,"Modular inverse of 11 mod 2^16 failed");
			assertEquals(fn.apply(11L, 8),163L,"Modular inverse of 11 mod 2^8 failed");
		};
	}
}
