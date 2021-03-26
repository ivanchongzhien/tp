package seedu.duke.parser;

import seedu.duke.commands.AddLessonCommand;
import seedu.duke.commands.AddModuleCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.EnterModuleCommand;
import seedu.duke.commands.ListTasksCommand;
import seedu.duke.commands.PrintHelpCommand;
import seedu.duke.exception.CommandException;
import seedu.duke.exception.ParserException;
import seedu.duke.module.ModuleList;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    //@@author ivanchongzhien
    @Test
    // DASHBOARD COMMAND
    void parse_dashboardCommandAddModule_addCommandObject() throws CommandException,
            ParserException {
        ModuleList.reset();

        Parser parser = new Parser();
        
        String input1 = "add CS2113T";
        Command command1 = parser.parse(input1);
        assertTrue(command1 instanceof AddModuleCommand);

        String input2 = "help";
        Command command2 = parser.parse(input2);
        assertTrue(command2 instanceof PrintHelpCommand);

        String input3 = "open cs1234";
        Command command3 = parser.parse(input3);
        assertTrue(command3 instanceof EnterModuleCommand);

        String input4 = "delete cs1234";
        Command command4 = parser.parse(input4);
        assertTrue(command4 instanceof AddModuleCommand);
    }

    @Test
    // IN MODULE COMMAND
    // list task command
    void parse_inModuleCommandListTask_ListTaskCommandObject() throws CommandException, ParserException {
        ModuleList.reset();
        ModuleList.hardSetSelectedModule("CS1234");

        Parser parser = new Parser();
        String input = "tasks";

        Command actualCommand = parser.parse(input);
        assertTrue(actualCommand instanceof ListTasksCommand);
    }

    @Test
    // add lesson command - all detail fields included
    void parse_inModuleCommandAddLessonFullDetails_addLessonObject() throws CommandException,
            ParserException {
        ModuleList.reset();
        ModuleList.hardSetSelectedModule("CS1234");

        Parser parser = new Parser();
        String input = "add lesson tutorial ;; Wednesday 9am-10am ;; www.zoom.com/1234 ;; Xianhao Cheng ;; " 
                + "xh123@nus.edu.sg";

        Command actualCommand = parser.parse(input);
        assertTrue(actualCommand instanceof AddLessonCommand);
    }


    @Test
    // test check indices method - providing various valid and invalid inputs
    // invalid inputs : out of bounds, duplicate index, non-integer inputs
    void checkIndices_variousInputs_processedArrayList() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        
        int max = 5;
        String input1 = "1 2 3"; // happy
        assertEquals(expected, Parser.checkIndices(input1, max));
        
        String input2 = "1 1 2 3 3 3 2 2 1 2"; // duplicates
        assertEquals(expected, Parser.checkIndices(input2, max));
        
        String input3 = "10 1 99 2 10909 3 99 99 100 10"; // out of bounds and duplicates
        assertEquals(expected, Parser.checkIndices(input3, max));
        
        String input4 = "10 1 -1 -99 -2 10918 2 3 -99 990 990 10 0 10"; // out of bounds and duplicates and negative
        assertEquals(expected, Parser.checkIndices(input4, max));
        
        String input5 = "10 1 -1 -99 -2 10918 2 3 abc"; // out of bounds and duplicates and negative and non-integer
        assertEquals(expected, Parser.checkIndices(input5, max));
    }
}