package edu.t1.javapro1.hw2;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamUtils {

    public StreamUtils() {
    }

    public List<String> method1(List<String> strings) {
        if (strings == null) {
            return Collections.emptyList();
        }

        return strings.stream()
                .filter(Objects::nonNull)
                .filter(string -> !string.isEmpty())
                .distinct()
                .toList();
    }

    public Optional<Integer> method2(List<String> strings) {
        if (strings == null) {
            return Optional.empty();
        }

        Set<Character> uniqueLetters = strings.stream()
                .filter(Objects::nonNull)
                .flatMap(string -> string.chars().mapToObj(c -> (char) c))
                .filter(Character::isLetter)
                .collect(Collectors.toSet());

        return Optional.of(uniqueLetters.size());
    }

    public Optional<String> method3(List<String> strings) {
        if (strings == null) {
            return Optional.empty();
        }

        return strings.stream()
                .filter(Objects::nonNull)
                .distinct()
                .max(Comparator.comparing(String::length));
    }

    public Optional<Integer> method4(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return Optional.empty();
        }

        return numbers.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst();
    }

    public List<String> method5(List<Employee> employees) {
        if (employees == null) {
            return Collections.emptyList();
        }

        String engineer = "engineer";

        return employees.stream()
                .filter(Objects::nonNull)
                .filter(employee -> Objects.equals(employee.role(), engineer))
                .sorted((o1, o2) -> o2.age() - o1.age())
                .map(Employee::name)
                .limit(3)
                .toList();
    }

    public OptionalDouble method6(List<Employee> employees) {
        if (employees == null) {
            return OptionalDouble.empty();
        }

        String engineer = "engineer";

        return employees.stream()
                .filter(Objects::nonNull)
                .filter(employee -> Objects.equals(employee.role(), engineer))
                .mapToInt(Employee::age)
                .average();
    }

    public Map<Integer, List<String>> method7(String sentence) {
        if (sentence == null) {
            return Collections.emptyMap();
        }

        return Pattern.compile("[,\\s\\.\\!\\?;:\\-\\[\\]\\{\\}\\(\\)\\t\\n\\r]+")
                .splitAsStream(sentence.trim())
                .filter(Predicate.not(String::isEmpty))
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.groupingBy(String::length));
    }

    public List<String> method8(List<String> sentences) {
        if (sentences == null) {
            return Collections.emptyList();
        }

        List<String> words = sentences.stream()
                .filter(Objects::nonNull)
                .flatMap(sentence ->
                        Arrays.stream(sentence.split("[,\\s\\.\\!\\?;:\\-\\[\\]\\{\\}\\(\\)\\t\\n\\r]+"))
                                .map(String::trim)
                                .filter(word -> !word.isEmpty())
                )
                .toList();

        if (words.isEmpty()) {
            return Collections.emptyList();
        }

        int maxLength = words.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        return words.stream()
                .filter(word -> word.length() == maxLength)
                .toList();
    }
}
