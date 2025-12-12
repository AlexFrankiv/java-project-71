package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

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
