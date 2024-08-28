package exercises2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTime_API {
    public static void main(String[] args) throws ParseException {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        System.out.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        ZonedDateTime zdt = ZonedDateTime.parse(ZonedDateTime.now(ZoneId.of("Australia/Sydney")).format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("Giờ hiện tại ở Australia là: "+zdt);

        LocalDateTime now =LocalDateTime.now();
        System.out.println(now);

        // Thời gian hiện tại
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));

        // Số ngày trong tháng hiện tại
        YearMonth yearMonth = YearMonth.now();
        int daysInMonth = yearMonth.lengthOfMonth();
        System.out.println("Số ngày trong tháng hiện tại: " + daysInMonth);

        //Số ngày trong năm hiện tại
        Year year = Year.now();
        int daysInYear = year.length();
        System.out.println("Số ngày trong năm hiện tại: " + daysInYear);

        // Chuyển một chuỗi ngày sang 1 đối tượng LocalDate
        String dateString = "2024-08-26";
        LocalDate date = LocalDate.parse(dateString);
        System.out.println("LocalDate từ chuỗi: " + date);

        // chuyển đổi một đối tượng LocalDate sang một chuỗi ngày (dd/MM/yyyy)
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Chuyển đổi một đối tượng LocalDate sang một chuỗi ngày(dd/MM/yyyy HH:mm)
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        LocalDate date1 = LocalDate.of(2024, 8, 26);
        LocalDate date2 = LocalDate.of(2023, 12, 31);

        int comparisonResult = date1.compareTo(date2);
        System.out.println("Kết quả so sánh: " + comparisonResult);

        LocalTime time1 = LocalTime.of(10, 30);
        LocalTime time2 = LocalTime.of(8, 45);

        int comparisonResult1 = time1.compareTo(time2);
        System.out.println("Kết quả so sánh: " + comparisonResult1);


        LocalDate dateTemplate = LocalDate.now().plusDays(7);
        System.out.println("Ngày sau 7 ngày: " + dateTemplate);

        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime minusTwoHours = dateTime.minusHours(2);
        System.out.println("Thời gian sau khi trừ 2 giờ: " + minusTwoHours);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);

        System.out.println("Ngày hôm qua: " + yesterday);
        System.out.println("Ngày mai: " + tomorrow);
    }

}
