package de.freizeitkarteosm.themeconfigurator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class ThemeOptionTest {

    @Test
    public void names() {
        final Locale locale = Locale.GERMAN;
        final MutableThemeOption option = new MutableThemeOption();

        assertEquals("", option.getName(locale));
        option.setName(locale, "Hallo Welt");
        assertEquals("Hallo Welt", option.getName(locale));
    }

    @Test
    public void status() {
        final MutableThemeOption option = new MutableThemeOption();

        option.enable();
        assertTrue(option.getStatus());
        option.disable();
        assertFalse(option.getStatus());
    }

    @Test
    public void lines() {
        final MutableThemeOption option = new MutableThemeOption();

        assertEquals(0, option.getStartLines().size());
        assertEquals(0, option.getEndLines().size());

        final List<Integer> lines = new ArrayList<Integer>();

        lines.add(20);
        lines.add(40);
        option.setStartLines(lines);

        lines.clear();
        lines.add(25);
        lines.add(45);
        option.setEndLines(lines);

        assertEquals(2, option.getStartLines().size());
        assertEquals(2, option.getEndLines().size());

        assertEquals(20, (int) option.getStartLines().get(0));
        assertEquals(25, (int) option.getEndLines().get(0));
    }
}
