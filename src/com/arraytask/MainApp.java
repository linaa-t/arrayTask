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

        ArrayRepository repository = ArrayRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();

        warehouse.registerToRepository(repository);

        try {
            List<String> lines = fileReader.readLinesFromFile(INPUT_FILE_PATH);
            int nextId = 1;

            for (String line : lines) {
                try {
                    IntArrayEntity tempEntity = parser.createIntArray(line);
                    IntArrayEntity entity = new IntArrayEntity(nextId, tempEntity.getNumbers());
                    repository.add(entity); // Директор добавляет и сам звонит Бухгалтеру!
                    nextId++;
                } catch (InvalidDataException e) {
                    LOGGER.warning("Пропущена строка: " + line);
                }
            }

            LOGGER.info("=== Статистика в Warehouse ===");
            List<IntArrayEntity> allEntities = repository.findAll();
            for (IntArrayEntity entity : allEntities) {
                ArrayStats stats = warehouse.getStats(entity.getId());
                LOGGER.info("ID=" + entity.getId() + ": " + stats.toString());
            }

            LOGGER.info("=== Поиск: сумма > 5 ===");
            BySumSpecification spec = new BySumSpecification(5, ComparisonType.GREATER);
            List<IntArrayEntity> found = repository.find(spec);
            for (IntArrayEntity entity : found) {
                LOGGER.info("Найден массив ID=" + entity.getId());
            }

            
            LOGGER.info("=== Сортировка по размеру ===");
            BySizeComparator sizeComparator = new BySizeComparator();
            List<IntArrayEntity> sorted = repository.sort(sizeComparator);
            for (IntArrayEntity entity : sorted) {
                LOGGER.info("ID=" + entity.getId() + ", размер=" + entity.getSize());
            }

           
            LOGGER.info("=== Меняем элемент: ID=1, индекс 0, значение 100 ===");
            repository.updateElement(1, 0, 100);
            ArrayStats newStats = warehouse.getStats(1);
            LOGGER.info("Новая статистика для ID=1: " + newStats.toString());

        } catch (FileOperationException e) {
            LOGGER.severe("Ошибка файла: " + e.getMessage());
        }
    }
}
