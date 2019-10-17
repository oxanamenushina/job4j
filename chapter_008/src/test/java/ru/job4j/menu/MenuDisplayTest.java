package ru.job4j.menu;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MenuDisplayTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MenuDisplayTest {

    private Menu menu = this.init();

    public Menu init() {
        Element e1 = new MenuElement("first", new ElementAction("Action 1 is performed."));
        Element e12 = new MenuElement("first.second", e1, new ElementAction("Action 1.2 is performed."));
        Element e2 = new MenuElement("second", new ElementAction("Action 2 is performed."));
        Element e3 = new MenuElement("third", new ElementAction("Action 3 is performed."));
        Element e11 = new MenuElement("first.first", e1, new ElementAction("Action 1.1 is performed."));
        Element e21 = new MenuElement("second.first", e2, new ElementAction("Action 2.1 is performed."));
        Element e121 = new MenuElement("first.second.first", e12, new ElementAction("Action 1.2.1 is performed."));
        Menu sm = new SummaryMenu();
        sm.addAll(List.of(e1, e2, e11, e21, e12, e121, e3));
        return sm;
    }

    @Test
    public void whenDysplayMenu() {
        Display display = new MenuDisplay(this.menu);
        List<String> text = display.getTextToDisplay();
        assertThat(text.get(0),
                is(new StringBuilder()
                        .append("Menu:").append(System.lineSeparator())
                        .append("1.first").append(System.lineSeparator())
                        .append("   1.1.first.first").append(System.lineSeparator())
                        .append("   1.2.first.second").append(System.lineSeparator())
                        .append("      1.2.1.first.second.first").append(System.lineSeparator())
                        .append("2.second").append(System.lineSeparator())
                        .append("   2.1.second.first").append(System.lineSeparator())
                        .append("3.third").append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }
}