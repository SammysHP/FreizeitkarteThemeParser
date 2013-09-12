package de.freizeitkarteosm.themeconfigurator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertEquals(-1, option.getStartLine());
        assertEquals(-1, option.getEndLine());

        option.setStartLine(20);
        option.setEndLine(25);

        assertEquals(20, option.getStartLine());
        assertEquals(25, option.getEndLine());
    }
}
