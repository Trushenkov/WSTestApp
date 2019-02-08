package sample;

/**
 * Date: 01.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public interface Helper {
    /**
     * Метод для перехода на другое окно приложения
     *
     * @param path путь к fxml файлу
     * @param title заголовок окна
     */
    void changeScreen(String path, String title);
}
