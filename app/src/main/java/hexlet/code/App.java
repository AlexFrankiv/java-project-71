package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

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
        try {
            String content1 = Files.readString(f1.toPath());
            String content2 = Files.readString(f2.toPath());

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> map1 = mapper.readValue(content1,new TypeReference<Map<String, Object>>() {});
            Map<String,Object> map2 = mapper.readValue(content2,new TypeReference<Map<String, Object>>() {});
            System.out.println(map1);
            System.out.println(map2);
            String diff = Differ.generate(map1,map2);
            System.out.println(diff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
