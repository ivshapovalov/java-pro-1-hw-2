package edu.t1.javapro1.hw2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamUtilsTest {

    private final StreamUtils streamUtils = new StreamUtils();

    @ParameterizedTest
    @MethodSource("method1Cases")
    void method1_returnValidResult(List<String> input, List<String> expected) {
        List<String> actual = streamUtils.method1(input);

        assertIterableEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("method2Cases")
    void method2_returnValidResult(List<String> input, Integer expected) {
        Optional<Integer> actual = streamUtils.method2(input);

        if (expected == null) {
            assertTrue(actual.isEmpty());
        } else {
            assertEquals(expected, actual.get());
        }
    }

    @ParameterizedTest
    @MethodSource("method3Cases")
    void method3_returnValidResult(List<String> input, String expected) {
        Optional<String> actual = streamUtils.method3(input);

        if (expected == null) {
            assertTrue(actual.isEmpty());
        } else {
            assertEquals(expected, actual.get());
        }
    }

    @ParameterizedTest
    @MethodSource("method4Cases")
    void method4_returnValidResult(List<Integer> input, Integer expected) {
        Optional<Integer> actual = streamUtils.method4(input);

        if (expected == null) {
            assertTrue(actual.isEmpty());
        } else {
            assertEquals(expected, actual.get());
        }
    }

    @ParameterizedTest
    @MethodSource("method5Cases")
    void method5_returnValidResult(List<Employee> input, List<String> expected) {
        List<String> actual = streamUtils.method5(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("method6Cases")
    void method6_returnValidResult(List<Employee> input, Double expected) {
        OptionalDouble actual = streamUtils.method6(input);

        if (expected == null) {
            assertTrue(actual.isEmpty());
        } else {
            assertEquals(expected, actual.getAsDouble());
        }
    }

    @ParameterizedTest
    @MethodSource("method7Cases")
    void method7_returnValidResult(String input, Map<Integer, List<String>> expected) {
        Map<Integer, List<String>> actual = streamUtils.method7(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("method8Cases")
    void method8_returnValidResult(List<String> input, List<String> expected) {
        List<String> actual = streamUtils.method8(input);

        assertEquals(expected, actual);
    }

    public static Stream<Arguments> method1Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(null, "", "ABC", null, "", "ABC", "ABc", "QQ", " "),
                        List.of("ABC", "ABc", "QQ", " ")),
                Arguments.of(
                        Arrays.asList(null, null),
                        Collections.emptyList()),
                Arguments.of(
                        null,
                        Collections.emptyList())
        );
    }

    public static Stream<Arguments> method2Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(null, "", "ABC", null, "CDE", "EE", "CDE", "ABC", " "),
                        5),
                Arguments.of(
                        Arrays.asList(null, null),
                        0),
                Arguments.of(
                        null,
                        null)
        );
    }

    public static Stream<Arguments> method3Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(null, "", "ABC", null, "CDE", "EE", "CDEF", "ABC", "GRFE", " "),
                        "CDEF"),
                Arguments.of(
                        Arrays.asList(null, null),
                        null),
                Arguments.of(
                        null,
                        null)
        );
    }

    public static Stream<Arguments> method4Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(5, 2, 10, 9, null, 4, 3, 10, 1, 13, null),
                        10),
                Arguments.of(
                        Arrays.asList(null, null),
                        null),
                Arguments.of(
                        null,
                        null)
        );
    }

    public static Stream<Arguments> method5Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Employee("A", "manager", 25),
                                new Employee("B", "manager", 35),
                                new Employee("C", "engineer", 35),
                                null,
                                new Employee("D", "engineer", 45),
                                new Employee("E", "engineer", 55),
                                new Employee("F", "qa", 55),
                                new Employee("G", "qa", 65),
                                new Employee("H", "engineer", 55),
                                null
                        ),
                        List.of("E", "H", "D")),
                Arguments.of(
                        Arrays.asList(null, null),
                        Collections.emptyList()),
                Arguments.of(
                        null,
                        Collections.emptyList())
        );
    }

    public static Stream<Arguments> method6Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Employee("A", "manager", 25),
                                new Employee("B", "manager", 35),
                                new Employee("C", "engineer", 35),
                                new Employee("D", "engineer", 45),
                                new Employee("E", "engineer", 55),
                                new Employee("F", "qa", 55),
                                new Employee("G", "qa", 65),
                                new Employee("H", "engineer", 65),
                                null
                        ),
                        50D),
                Arguments.of(
                        Arrays.asList(null, null),
                        null),
                Arguments.of(
                        null,
                        null)
        );
    }

    public static Stream<Arguments> method7Cases() {
        return Stream.of(
                Arguments.of(
                        "Мама мыла Окно, окно было довольно. довольно. 12345678",
                        Map.of(4, List.of("мама", "мыла", "окно", "было"), 8, List.of("довольно", "12345678"))),
                Arguments.of(
                        "     ",
                        Collections.emptyMap()),
                Arguments.of(
                        "",
                        Collections.emptyMap()),
                Arguments.of(
                        ".,;",
                        Collections.emptyMap()),
                Arguments.of(
                        null,
                        Collections.emptyMap())
        );
    }

    public static Stream<Arguments> method8Cases() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(null, "Мама мыла Окно", " окно было довольно", "кровать", " "),
                        List.of("довольно")),
                Arguments.of(
                        Arrays.asList(null, "Мама мыла Окно", " окно было довольно", "кровать", "довольно  12345678", " "),
                        List.of("довольно", "довольно", "12345678")),
                Arguments.of(
                        Arrays.asList(null, "   ", " ", ""),
                        Collections.emptyList()),
                Arguments.of(
                        Arrays.asList(null, null),
                        Collections.emptyList()),
                Arguments.of(
                        List.of(""),
                        Collections.emptyList()),
                Arguments.of(
                        List.of(".,;", ",;"),
                        Collections.emptyList()),
                Arguments.of(
                        null,
                        Collections.emptyList())
        );
    }
}
