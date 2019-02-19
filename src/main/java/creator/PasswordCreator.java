package creator;

import com.beust.jcommander.JCommander;
import creator.cl.Args;
import creator.http.RandomContainer;
import creator.http.RandomOrgResponse;
import creator.http.RandomResult;
import creator.http.RequestMaker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PasswordCreator {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        Long length = arguments.getLength();
        Integer number = arguments.getNumber();
        String filename = arguments.getFilename();

        RandomOrgResponse randomOrgResponse
                = RequestMaker.requestPasswords(number, length);

        List<String> passwords = extractPasswords(randomOrgResponse);

        StringBuilder builder = new StringBuilder();

        passwords.forEach(l -> builder.append(l).append("\n"));

        Path path = Paths.get(filename);

        boolean exists = Files.exists(path);

        if (!exists) {

            Files.createFile(path);

        }

        Files.write(path, builder.toString().getBytes(), StandardOpenOption.APPEND);

    }

    private static List<String> extractPasswords(final RandomOrgResponse randomOrgResponse) {
        RandomResult result
                = randomOrgResponse.getResult();

        RandomContainer random = result.getRandom();

        return random.getData();
    }

}
