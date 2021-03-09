package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrintHelpCommandTest {

    @Test
    void execute_noInput_expectAllCommandsWithDescription() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ModuleList modules = new ModuleList();
        modules.addModule("CS2105");
        modules.addModule("CS2106");

        // no module selected
        ModuleList.selectedModule = null;

        // execute command
        Command command1 = new PrintHelpCommand();
        command1.execute(modules, new UI());

        String output = PrintHelpCommand.HELP_MESSAGE;
        assertEquals(output + System.lineSeparator(), outContent.toString());

        // module selected
        ModuleList.selectedModule = new Module("CS2105");
        Command command2 = new PrintHelpCommand();
        command2.execute(modules, new UI());

        System.setOut(originalOut);
    }

    @Test
    void isExit_noInput_expectFalse() {
        assertEquals(false, new PrintHelpCommand().isExit());
    }
}