package ru.job4j.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class InteractCalcTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final Consumer<String> output = new Consumer<>() {

        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(System.out);
    }

    @Test
    public void whenAdd8And9ThenInteractCalcReturnResult17() {
        Calculator calc = new Calculator();
        Actions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("+", "8.3", "9", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(), is("Result: 17.3" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void whenFrom8Subtract4ThenInteractCalcReturnResult4() {
        Calculator calc = new Calculator();
        Actions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("-", "8", "4", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(), is("Result: 4.0" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void when7MultiplyBy8ThenInteractCalcReturnResult56() {
        Calculator calc = new Calculator();
        Actions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("*", "7.2", "8", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(), is("Result: 57.6" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void when8DivideBy4ThenInteractCalcReturnResult2() {
        Calculator calc = new Calculator();
        Actions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("/", "8", "4", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(), is("Result: 2.0" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void whenAdd8And2AndResultDivideBy5ThenInteractCalcReturnResult10And25() {
        Calculator calc = new Calculator();
        Actions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("+", "8", "2", "/", "m", "4", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is("Result: 10.0" + System.lineSeparator() + System.lineSeparator()
                        + "Result: 2.5" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void when100DivideBy20AndFrom17SubtractResultThenInteractCalcReturnResult5And12() {
        Calculator calc = new Calculator();
        InterCalcActions acts = new InterCalcActions(calc);
        InteractCalc ic = new InteractCalc(new StubInput(List.of("/", "100", "20", "-", "17", "m", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is("Result: 5.0" + System.lineSeparator() + System.lineSeparator()
                        + "Result: 12.0" + System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    public void whenSin30ThenInteractCalcReturnResult05() {
        EngCalculator calc = new EngCalculator();
        Actions acts = new EngCalcActions(calc, new InterCalcActions(new Calculator()));
        InteractCalc ic = new InteractCalc(new StubInput(List.of("sin", "30", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is(new StringBuilder()
                        .append("Result: ")
                        .append(Math.sin(Math.toRadians(30)))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
    }

    @Test
    public void whenCos180ThenInteractCalcReturnResultMinus1() {
        EngCalculator calc = new EngCalculator();
        Actions acts = new EngCalcActions(calc, new InterCalcActions(new Calculator()));
        InteractCalc ic = new InteractCalc(new StubInput(List.of("cos", "180", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is(new StringBuilder()
                        .append("Result: ")
                        .append(Math.cos(Math.toRadians(180)))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
    }

    @Test
    public void whenTangent45ThenInteractCalcReturnResult1() {
        EngCalculator calc = new EngCalculator();
        Actions acts = new EngCalcActions(calc, new InterCalcActions(new Calculator()));
        InteractCalc ic = new InteractCalc(new StubInput(List.of("tg", "45", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is(new StringBuilder()
                        .append("Result: ")
                        .append(Math.tan(Math.toRadians(45)))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
    }

    @Test
    public void whenSin90And8MultiplyByResultThenInteractCalcReturnResult8() {
        EngCalculator calc = new EngCalculator();
        Actions acts = new EngCalcActions(calc, new InterCalcActions(new Calculator()));
        InteractCalc ic = new InteractCalc(new StubInput(List.of("sin", "90", "*", "8", "m", "exit"),
                new CheckInput(acts)), output, acts);
        ic.calculate();
        assertThat(out.toString(),
                is(new StringBuilder()
                        .append("Result: ")
                        .append(Math.sin(Math.toRadians(90)))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("Result: ")
                        .append(Math.sin(Math.toRadians(90)) * 8)
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
    }
}