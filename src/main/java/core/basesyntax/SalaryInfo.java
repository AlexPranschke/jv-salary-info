package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            builder.append(name).append(" - ");
            int salary = 0;
            for (String record : data) {
                String[] recordData = record.split(" ");
                LocalDate recordDate = LocalDate.parse(recordData[0], formatter);
                if (recordData[1].equals(name)
                        && (recordDate.isAfter(from) || recordDate.isEqual(from))
                        && (recordDate.isBefore(to) || recordDate.isEqual(to))) {
                    salary += Integer.parseInt(recordData[2]) * Integer.parseInt(recordData[3]);
                }
            }
            builder.append(salary).append("\n");

        }
        return builder.toString();
    }
}
