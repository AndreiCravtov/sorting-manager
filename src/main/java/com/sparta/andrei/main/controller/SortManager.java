package com.sparta.andrei.main.controller;

import com.sparta.andrei.errors.InvalidRange;
import com.sparta.andrei.errors.LengthIsNegative;
import com.sparta.andrei.errors.SorterIsNull;
import com.sparta.andrei.logging.Logging;
import com.sparta.andrei.main.model.RandomModel;
import com.sparta.andrei.main.model.SortModel;
import com.sparta.andrei.main.view.DisplayManager;
import com.sparta.andrei.sorters.SorterFactory;

import java.util.stream.Collectors;

public class SortManager {
    RandomModel randomModel;
    SortModel sortModel;
    DisplayManager displayManager;

    SortManager() {
        randomModel = new RandomModel();
        sortModel = new SortModel();
        displayManager = new DisplayManager();
    }

    public void start() {
        displayManager.welcome();
        DisplayManager.UserOptions options = displayManager.getUserOptions();

        Logging.logger.info("User chose a length of random array to be %s".formatted(options.length));
        Logging.logger.info("User chose a the lower bound of numbers in the array to be %s".formatted(options.lower));
        Logging.logger.info("User chose a the upper bound of numbers in the array to be %s".formatted(options.upper));
        Logging.logger.info("User chose the following arrays to sort with: %s".formatted(options.types.stream().map(i -> i.getName()+". ").collect(Collectors.joining())));

        try {
            randomModel.setArray(options.length, options.lower, options.upper);
        } catch (LengthIsNegative | InvalidRange e) {
            Logging.logger.error(e);
            throw new RuntimeException(e);
        }
        sortModel.setUnsorted(randomModel.getArray());

        for (SorterFactory.SorterType type: options.types) {
            sortModel.setSorter(type);
            long timeTaken = 0;
            try {
                timeTaken = sortModel.sort();
            } catch (SorterIsNull e) {
                Logging.logger.error(e);
                throw new RuntimeException(e);
            }
            Logging.logger.info("Sorting the array with %s took %s ns".formatted(type.getName(), timeTaken));
            displayManager.displayStats(type, timeTaken);
        }
        displayManager.displayArrays(sortModel.getUnsorted(), sortModel.getSorted());

        displayManager.goodbye();
    }
}
