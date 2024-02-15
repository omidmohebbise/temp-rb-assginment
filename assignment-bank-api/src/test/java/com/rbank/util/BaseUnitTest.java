package com.rbank.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@Tag("unit")
@ExtendWith(SpringExtension.class)
public class BaseUnitTest {


}
