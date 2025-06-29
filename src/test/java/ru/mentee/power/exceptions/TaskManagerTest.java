package ru.mentee.power.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class TaskManagerTest {

    private TaskManager account;
    private static final double INITIAL_BALANCE = 1000.0;
    private static final String ACCOUNT_ID = "ACC-123";

    @BeforeEach
    void setUp() {
        account = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Конструктор должен правильно устанавливать начальный баланс и ID")
    void constructorShouldSetInitialBalanceAndId() {
        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Конструктор должен выбрасывать IllegalArgumentException при отрицательном балансе")
    void constructorShouldThrowIllegalArgumentExceptionForNegativeBalance() {
        assertThatThrownBy(() -> new TaskManager(ACCOUNT_ID, -100.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Начальный баланс не может быть отрицательным");
    }

    // --- Тесты для deposit ---
    @Test
    @DisplayName("Метод deposit должен увеличивать баланс при положительной сумме")
    void depositShouldIncreaseBalanceForPositiveAmount() {
        account.deposit(500.0);
        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE + 500.0);
    }

    @Test
    @DisplayName("Метод deposit должен допускать нулевую сумму")
    void depositShouldAllowZeroAmount() {
        account.deposit(0.0);
        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Метод deposit должен выбрасывать IllegalArgumentException при отрицательной сумме")
    void depositShouldThrowIllegalArgumentExceptionForNegativeAmount() {
        double before = account.getBalance();
        assertThatThrownBy(() -> account.deposit(-50.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Сумма депозита не может быть отрицательной");
        assertThat(account.getBalance()).isEqualTo(before);
    }

    // --- Тесты для withdraw ---
    @Test
    @DisplayName("Метод withdraw должен уменьшать баланс при корректной сумме")
    void withdrawShouldDecreaseBalanceForValidAmount() throws TaskValidationException {
        account.withdraw(300.0);
        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE - 300.0);
    }

    @Test
    @DisplayName("Метод withdraw должен позволять снять полный баланс")
    void withdrawShouldAllowWithdrawingFullBalance() throws TaskValidationException {
        account.withdraw(INITIAL_BALANCE);
        assertThat(account.getBalance()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Метод withdraw должен допускать нулевую сумму")
    void withdrawShouldAllowZeroAmount() throws TaskValidationException {
        account.withdraw(0.0);
        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Метод withdraw должен выбрасывать IllegalArgumentException при отрицательной сумме")
    void withdrawShouldThrowIllegalArgumentExceptionForNegativeAmount() {
        double before = account.getBalance();
        assertThatThrownBy(() -> account.withdraw(-200.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Сумма снятия не может быть отрицательной");
        assertThat(account.getBalance()).isEqualTo(before);
    }

    @Test
    @DisplayName("Метод withdraw должен выбрасывать TaskValidationException при превышении баланса")
    void withdrawShouldThrowTaskValidationExceptionWhenAmountExceedsBalance() {
        double excessiveAmount = INITIAL_BALANCE + 100.0;
        assertThatThrownBy(() -> account.withdraw(excessiveAmount))
                .isInstanceOf(TaskValidationException.class)
                .satisfies(ex -> {
                    TaskValidationException e = (TaskValidationException) ex;
                    assertThat(e.getMessage()).contains("Недостаточно средств на счете");
                    assertThat(e.getBalance()).isEqualTo(INITIAL_BALANCE);
                    assertThat(e.getWithdrawAmount()).isEqualTo(excessiveAmount);
                    assertThat(e.getDeficit()).isEqualTo(excessiveAmount - INITIAL_BALANCE);
                });

        assertThat(account.getBalance()).isEqualTo(INITIAL_BALANCE);
    }
}