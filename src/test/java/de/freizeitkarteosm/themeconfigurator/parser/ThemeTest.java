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

    private static String loadFile(final File file) throws IOException {
        final StringBuilder content = new StringBuilder();

        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        bufferedReader.close();

        return content.toString();
    }

    @Test
    public void parse() throws IOException {
        final Theme theme = new Theme(loadFile(EXAMPLE_THEME));

        // Groups
        final List<ThemeOptionGroup> groups = theme.getGroups();
        assertEquals(3, groups.size());

        assertEquals("Flächen", groups.get(0).getName(Locale.GERMAN));
        assertEquals("Punkte", groups.get(2).getName(Locale.GERMAN));

        // Options
        final List<ThemeOption> options = groups.get(0).getOptions();
        assertEquals(2, options.size());
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

    @Test
    public void compileUnmodified() throws IOException {
        final Theme themeA = new Theme(loadFile(EXAMPLE_THEME));
        final Theme themeB = new Theme(themeA.compile());

        final List<ThemeOption> optionsA = themeA.getOptions();
        final List<ThemeOption> optionsB = themeB.getOptions();

        assertEquals(optionsA.size(), optionsB.size());

        for (int i = 0; i < optionsA.size(); i++) {
            ThemeOption optionA = optionsA.get(i);
            ThemeOption optionB = optionsB.get(i);

            assertEquals(optionA.getId(), optionB.getId());
            assertEquals(optionA.getStatus(), optionB.getStatus());
        }
    }

    @Test
    public void compileModified() throws IOException {
        final Theme themeA = new Theme(loadFile(EXAMPLE_THEME));
        final List<ThemeOption> optionsA = themeA.getOptions();

        // enable all
        for (ThemeOption option : optionsA) {
            option.enable();
        }

        final Theme themeB = new Theme(themeA.compile());
        final List<ThemeOption> optionsB = themeB.getOptions();

        assertEquals(optionsA.size(), optionsB.size());

        for (ThemeOption option : optionsB) {
            assertTrue(option.getStatus());
        }

        // disable all
        for (ThemeOption option : optionsA) {
            option.disable();
        }

        final Theme themeC = new Theme(themeA.compile());
        final List<ThemeOption> optionsC = themeC.getOptions();

        assertEquals(optionsA.size(), optionsC.size());

        for (ThemeOption option : optionsC) {
            assertFalse(option.getStatus());
        }
    }
}
