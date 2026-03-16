package assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junitpioneer.jupiter.DisableIfTestFails;

import studio.programkode.jatf.java25.BaseAssignment;
import static studio.programkode.jatf.java25.Framework.*;


@DisableIfTestFails
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Assignment Title")
public class TestAssignment extends BaseAssignment
{
    @DisplayName("Task #01 Title")
    @Test
    @Order(1)
    void task01() {
        // Code that tests assignment goes here
    }
}
