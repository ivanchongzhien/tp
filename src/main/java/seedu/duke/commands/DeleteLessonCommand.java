package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.commands.AddLessonCommand.getLessonTypeName;

public class DeleteLessonCommand extends Command {
    public static final String MESSAGE_REMOVED_LESSON = "Removed %s";
    public static final String MESSAGE_LESSSON_OPTIONS = "%d. %s%n";
    public static final String MESSAGE_DELETE_LESSONS = "Which lessons would you like to delete?";
    private final Scanner commandLineReader = new Scanner(System.in);

    public DeleteLessonCommand() {
        System.out.println(MESSAGE_DELETE_LESSONS);

    }

    public Scanner getCommandLineReader() {
        return commandLineReader;
    }

    @Override
    public void execute(ModuleList moduleList, UI ui) {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        printLessonOptions(lessonList);

        Scanner input = getCommandLineReader();
        String line = input.nextLine();
        ArrayList<Integer> indexes = Parser.checkIndices(line, lessonList.size());

        deleteLessonsFromList(module, lessonList, indexes);
    }

    private void deleteLessonsFromList(Module module, ArrayList<Lesson> lessonList, ArrayList<Integer> indexes) {
        int pointer = 1;
        for (int index : indexes) {
            int modifiedIndex = index - pointer;
            System.out.println(index);
            Lesson lesson = lessonList.get(modifiedIndex);
            String lessonName = getLessonName(lesson);
            System.out.println(String.format(MESSAGE_REMOVED_LESSON, lessonName));
            module.deleteLessonFromList(lessonList, modifiedIndex);
            pointer++;
        }
    }

    public static String getLessonName(Lesson lesson) {
        LessonType newLessonType = lesson.getLessonType();
        return getLessonTypeName(newLessonType);
    }

    public static void printLessonOptions(ArrayList<Lesson> lessonList) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonName = getLessonName(lesson);
            System.out.printf(MESSAGE_LESSSON_OPTIONS, counter, lessonName);
            counter++;
        }
    }
}
