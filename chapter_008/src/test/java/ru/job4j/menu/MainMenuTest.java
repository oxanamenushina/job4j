package ru.job4j.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MainMenuTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainMenuTest {

    private Menu menu = this.init();

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    public Menu init() {
        Element e1 = new MenuElement("first", new ElementAction("Action 1 is performed."));
        Element e12 = new MenuElement("first.second", e1, new ElementAction("Action 1.2 is performed."));
        Element e2 = new MenuElement("second", new ElementAction("Action 2 is performed."));
        Element e11 = new MenuElement("first.first", e1, new ElementAction("Action 1.1 is performed."));
        Element e21 = new MenuElement("second.first", e2, new ElementAction("Action 2.1 is performed."));
        Element e121 = new MenuElement("first.second.first", e12, new ElementAction("Action 1.2.1 is performed."));
        Menu sm = new SummaryMenu();
        sm.addAll(List.of(e1, e2, e11, e21, e12, e121));
        return sm;
    }

    @Test
    public void whenElement12IsSelectedThenExecuteActionOfElement12() {
        MainMenu mm = new MainMenu(
                this.menu,
                new MenuDisplay(this.menu),
                new ValidateInput(new StubInput(List.of("1.2.", "e"), this.menu.getMenu().keySet()))
        );
        mm.init();
        assertThat(mem.toString(), is("Action 1.2 is performed." + System.lineSeparator()));
    }

    @Test
    public void whenInvalidInput() {
        MainMenu mm = new MainMenu(
                this.menu,
                new MenuDisplay(this.menu),
                new ValidateInput(new StubInput(List.of("invalid", "e"), this.menu.getMenu().keySet()))
        );
        mm.init();
        assertThat(mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenElement2IsSelectedAndElement121IsSelectedThenExecuteActionOfElement2AndActionOfElement121() {
        MainMenu mm = new MainMenu(
                this.menu,
                new MenuDisplay(this.menu),
                new ValidateInput(new StubInput(List.of("2.", "1.2.1.", "e"), this.menu.getMenu().keySet()))
        );
        mm.init();
        assertThat(mem.toString(), is(new StringBuilder()
                .append("Action 2 is performed.")
                .append(System.lineSeparator())
                .append("Action 1.2.1 is performed.")
                .append(System.lineSeparator())
                .toString())
        );
    }
}