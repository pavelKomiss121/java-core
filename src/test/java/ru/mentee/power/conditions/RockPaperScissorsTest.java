package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RockPaperScissorsTest {

    private RockPaperScissors game;

    private static final String ROCK = "Камень";
    private static final String PAPER = "Бумага";
    private static final String SCISSORS = "Ножницы";
    private static final String PLAYER_WINS = "Победа игрока";
    private static final String COMPUTER_WINS = "Победа компьютера";
    private static final String DRAW = "Ничья";
    private static final String ERROR = "Ошибка";

    @BeforeEach
    void setUp() {
        game = new RockPaperScissors();
    }

    @Test
    @DisplayName("Камень побеждает ножницы")
    void rockBeatsScissors() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = SCISSORS;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Исправьте ошибку в ожидаемом результате!
        // Камень бьет ножницы, поэтому игрок должен победить
        assertThat(result).isEqualTo(PLAYER_WINS); // В этой строке ошибка!
    }

    @Test
    @DisplayName("Ножницы побеждают бумагу")
    void scissorsBeatsPaper() {
        // Arrange
        String playerChoice = SCISSORS;
        String computerChoice = PAPER;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(PLAYER_WINS);
    }

    @Test
    @DisplayName("Бумага побеждает камень")
    void paperBeatsRock() {
        // Arrange
        // TODO: Исправьте ошибку в выборе компьютера!
        // Для проверки того, что бумага побеждает камень, компьютер должен выбрать камень
        String playerChoice = PAPER;
        String computerChoice = ROCK; // В этой строке ошибка!

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(PLAYER_WINS);
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Камень)")
    void drawWhenSameChoiceRock() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = ROCK;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Исправьте ошибку в ожидаемом результате!
        // При одинаковом выборе должна быть ничья
        assertThat(result).isEqualTo(DRAW); // В этой строке ошибка!
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Бумага)")
    void drawWhenSameChoicePaper() {
        // Arrange
        String playerChoice = PAPER;
        String computerChoice = PAPER;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Ножницы)")
    void drawWhenSameChoiceScissors() {
        // Arrange
        String playerChoice = SCISSORS;
        String computerChoice = SCISSORS;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Обработка некорректного выбора игрока")
    void handleInvalidPlayerChoice() {
        // Arrange
        String playerChoice = "Колодец"; // Некорректный выбор
        String computerChoice = ROCK;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Исправьте ошибку в ожидаемом результате!
        // При некорректном выборе должна возвращаться ошибка
        assertThat(result).isEqualTo(ERROR); // В этой строке ошибка!
    }

    @Test
    @DisplayName("Обработка некорректного выбора компьютера")
    void handleInvalidComputerChoice() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = "Огонь"; // Некорректный выбор

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Исправьте ошибку в ожидаемом результате!
        // При некорректном выборе должна возвращаться ошибка
        assertThat(result).isEqualTo(ERROR); // В этой строке ошибка!
    }

    @Test
    @DisplayName("Обработка некорректного выбора у обоих")
    void handleInvalidBothChoices() {
        // Arrange
        String playerChoice = "Вода";
        String computerChoice = "Воздух";

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(ERROR);
    }

    @RepeatedTest(10) // Повторим тест 10 раз для большей уверенности в случайности
    @DisplayName("Генерация случайного выбора компьютера")
    void generateComputerChoiceReturnsValidOption() {
        // Act
        String choice = game.generateComputerMove();

        // Assert
        // TODO: Исправьте ошибку в проверке допустимых вариантов!
        // Метод должен возвращать один из допустимых вариантов: Камень, Ножницы или Бумага
        assertThat(choice.toLowerCase()).isIn(ROCK.toLowerCase(), SCISSORS.toLowerCase(), PAPER.toLowerCase()); // В этой строке ошибка!
    }

    @ParameterizedTest
    @CsvSource({ // playerChoice, computerChoice, expectedResult
            "Камень, Ножницы, Победа игрока",
            "Ножницы, Камень, Победа компьютера",
            "Бумага, Бумага, Ничья",
            "Ножницы, Бумага, Победа игрока",
            // TODO: Исправьте ошибки в ожидаемых результатах!
            "Камень, Бумага, Победа компьютера",  // В этой строке ошибка! Бумага бьет камень → Победа компьютера
            "Бумага, Ножницы, Победа компьютера"   // В этой строке ошибка! Ножницы бьют бумагу → Победа компьютера
    })
    @DisplayName("Различные комбинации выборов для determineWinner")
    void testVariousChoiceCombinationsDetermineWinner(String playerChoice, String computerChoice, String expectedResult) {
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(expectedResult);
    }
}