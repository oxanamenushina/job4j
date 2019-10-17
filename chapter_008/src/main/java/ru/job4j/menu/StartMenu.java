package ru.job4j.menu;

import java.util.List;

/**
 * StartMenu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartMenu {

    public static void main(String[] args) {
        Element e1 = new MenuElement("first", new ElementAction("Action 1 is performed."));
        Element e12 = new MenuElement("first.second", e1, new ElementAction("Action 1.2 is performed."));
        Element e2 = new MenuElement("second", new ElementAction("Action 2 is performed."));
        Element e3 = new MenuElement("third", new ElementAction("Action 3 is performed."));
        Element e11 = new MenuElement("first.first", e1, new ElementAction("Action 1.1 is performed."));
        Element e21 = new MenuElement("second.first", e2, new ElementAction("Action 2.1 is performed."));
        Element e121 = new MenuElement("first.second.first", e12, new ElementAction("Action 1.2.1 is performed."));
        Menu sm = new SummaryMenu();
        sm.addAll(List.of(e1, e2, e11, e21, e12, e121, e3));
        MainMenu mm = new MainMenu(sm, new MenuDisplay(sm), new ValidateInput(new ConsoleUI(sm.getMenu().keySet())));
        mm.init();
    }
}