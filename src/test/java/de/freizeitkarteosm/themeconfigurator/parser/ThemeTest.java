package de.freizeitkarteosm.themeconfigurator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class ThemeTest {
    private static final File EXAMPLE_THEME = new File("src/test/resources/freizeitkarte.xml");

    @Test
    public void parse() throws IOException {
        // Load file and parse theme
        final StringBuilder content = new StringBuilder();

        final FileReader fileReader = new FileReader(EXAMPLE_THEME);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        bufferedReader.close();

        Theme theme = new Theme(content.toString());

        // Groups
        final List<ThemeOptionGroup> groups = theme.getGroups();

        assertEquals("Flächen", groups.get(0).getName(Locale.GERMAN));
        assertEquals("Punkte", groups.get(2).getName(Locale.GERMAN));

        // Options
        final List<ThemeOption> options = groups.get(0).getOptions();
        ThemeOption option;

        option = options.get(0);
        assertEquals("Ackerland", option.getName(Locale.GERMAN));
        assertEquals(316, option.getStartLine());
        assertEquals(326, option.getEndLine());
        assertFalse(option.getStatus());

        option = options.get(1);
        assertEquals("Naturschutzgebiete", option.getName(Locale.GERMAN));
        assertEquals(1477, option.getStartLine());
        assertEquals(1484, option.getEndLine());
        assertTrue(option.getStatus());
    }
}
