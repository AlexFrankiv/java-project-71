package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

class App implements Runnable {

    @Parameters(index = "0",
            description = "path to first file",
            paramLabel = "filepath1")
            File f1;

    @Parameters(index = "1",
            description = "path to second file",
            paramLabel = "filepath2")
            File f2;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
            String format;

    @Option(names = {"-V", "--version"},
            versionHelp = true,
            description = "Print version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Override
    public void run() {

    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
