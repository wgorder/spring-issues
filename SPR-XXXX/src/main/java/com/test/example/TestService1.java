package com.test.example;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TestService1 {
	void doWork(@NotEmpty String string1, @NotEmpty String string2);
}
