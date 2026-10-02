package com.arraytask;

import com.arraytask.comparator.ByIdComparator;
import com.arraytask.comparator.BySizeComparator;
import com.arraytask.entity.IntArrayEntity;
import com.arraytask.exception.FileOperationException;
import com.arraytask.exception.InvalidDataException;
import com.arraytask.repository.ArrayRepository;
import com.arraytask.service.FileReaderService;
import com.arraytask.specification.BySumSpecification;
import com.arraytask.specification.ComparisonType;
import com.arraytask.util.DataParser;
import com.arraytask.warehouse.ArrayStats;
import com.arraytask.warehouse.Warehouse;
import java.util.List;
import java.util.logging.Logger;

public class MainApp {

    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());
    private static final String INPUT_FILE_PATH = "data/input.txt";

    public static void main(String[] args) {
        FileReaderService fileReader = new FileReaderService();
        DataParser parser = new DataParser();

        // Получаем наших "Одиночек" (Singletons)
        ArrayRepository repository = ArrayRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();

        // Подписываем Бухгалтера на уведомления от Директора
        warehouse.registerToRepository(repository);

        try {
            List<String> lines = fileReader.readLinesFromFile(INPUT_FILE_PATH);
            int nextId = 1;

            // 1. Читаем файл и добавляем в Репозиторий
            for (String line : lines) {
                try {
                    IntArrayEntity tempEntity = parser.createIntArray(line);
                    // Создаем настоящий объект с ID
                    IntArrayEntity entity = new IntArrayEntity(nextId, tempEntity.getNumbers());
                    repository.add(entity); // Директор добавляет и сам звонит Бухгалтеру!
                    nextId++;
                } catch (InvalidDataException e) {
                    LOGGER.warning("Пропущена строка: " + line);
                }
            }

            // 2. Смотрим, что насчитал Бухгалтер
            LOGGER.info("=== Статистика в Warehouse ===");
            List<IntArrayEntity> allEntities = repository.findAll();
            for (IntArrayEntity entity : allEntities) {
                ArrayStats stats = warehouse.getStats(entity.getId());
                LOGGER.info("ID=" + entity.getId() + ": " + stats.toString());
            }

            // 3. Ищем массивы с суммой больше 5
            LOGGER.info("=== Поиск: сумма > 5 ===");
            BySumSpecification spec = new BySumSpecification(5, ComparisonType.GREATER);
            List<IntArrayEntity> found = repository.find(spec);
            for (IntArrayEntity entity : found) {
                LOGGER.info("Найден массив ID=" + entity.getId());
            }

            // 4. Сортируем по размеру массива
            LOGGER.info("=== Сортировка по размеру ===");
            BySizeComparator sizeComparator = new BySizeComparator();
            List<IntArrayEntity> sorted = repository.sort(sizeComparator);
            for (IntArrayEntity entity : sorted) {
                LOGGER.info("ID=" + entity.getId() + ", размер=" + entity.getSize());
            }

            // 5. МЕНЯЕМ элемент и смотрим, как Бухгалтер пересчитает!
            LOGGER.info("=== Меняем элемент: ID=1, индекс 0, значение 100 ===");
            repository.updateElement(1, 0, 100);
            ArrayStats newStats = warehouse.getStats(1);
            LOGGER.info("Новая статистика для ID=1: " + newStats.toString());

        } catch (FileOperationException e) {
            LOGGER.severe("Ошибка файла: " + e.getMessage());
        }
    }
}