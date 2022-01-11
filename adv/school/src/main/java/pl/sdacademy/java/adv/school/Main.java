package pl.sdacademy.java.adv.school;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        //try with resources
        List<Student> students = new LinkedList<>(); //mutable
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            if (studentsDataStream == null) {
                return;
            }
            Scanner scanner = new Scanner(studentsDataStream);
            int lineNumber = 0;
            while(scanner.hasNextLine()){
                lineNumber++;
                String line = scanner.nextLine();
                if (StringUtils.isBlank(line)) {
                    LOGGER.info("Skipping line [{}]", lineNumber);
                    continue;
                }
                LOGGER.info("Parsing line [{}]: {}", lineNumber, line);
                //parseStudent(line).ifPresent(student -> students.add(student));
                parseStudent(line).ifPresent(students::add);
            }
        }

    }

    public static Optional<Student> parseStudent(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        List<String> strings = new ArrayList<>();
        while (scanner.hasNext()) {
            String next = scanner.next();
            strings.add(StringUtils.strip(next, "\""));
        }
        if (strings.size() != 10) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }
        Student student = new Student();
        try {
            student.setId(strings.get(0));
            student.setLastName(strings.get(1));
            student.setFirstName(strings.get(2));
            student.setStartYear(Short.parseShort(strings.get(3)));
            student.setSchoolYear(Byte.parseByte(strings.get(4)));
            student.setClassCode(strings.get(5).charAt(0));
            LocalDate birthDate = LocalDate.of(Integer.parseInt(strings.get(6)), Integer.parseInt(strings.get(7)),
                    Integer.parseInt(strings.get(8)));
            student.setBirthDate(birthDate);
            student.setCity(strings.get(9));
        } catch (Exception e) {
            LOGGER.error("Parsing error", e);
            return Optional.empty();
        }
        return Optional.of(student);

    }

}