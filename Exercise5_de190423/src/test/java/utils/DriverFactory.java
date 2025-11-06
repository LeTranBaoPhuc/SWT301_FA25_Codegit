package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ✅ Cho phép JavaScript (đây là mặc định, nên bỏ prefs kia đi)
        // options.setExperimentalOption("prefs", Map.of()); // Không cần cấu hình JS nữa

        // ✅ Một số tuỳ chọn ổn định khi chạy test
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");

        // Nếu bạn muốn chạy ẩn không mở trình duyệt GUI:
        // options.addArguments("--headless=new");

        return new ChromeDriver(options);
    }
}
