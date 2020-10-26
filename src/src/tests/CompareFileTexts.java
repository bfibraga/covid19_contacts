package tests;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CompareFileTexts
{
    private static final String ERROR_TEST = "Not the same text\nCheck line: ";
    private static final String SUCCESS_TEST = "Success! :D\nNumber lines readed: ";

    private BufferedReader output_reader;
    private BufferedReader test_file_reader;

    public CompareFileTexts(String output_path, String text_file_path) throws FileNotFoundException {
        FileReader output_filereader = new FileReader(output_path);
        FileReader test_filereader = new FileReader(text_file_path);

        if (output_reader == null || test_filereader == null)
            throw new FileNotFoundException();

        output_reader =  new BufferedReader(output_filereader);
        test_file_reader = new BufferedReader(test_filereader);
    }

    public String find_diff() throws IOException {
        String line_output = output_reader.readLine();
        String line_test = test_file_reader.readLine();
        int n_line = 0;

        while (line_output != null && line_test != null){
            if (!line_output.equals(line_test))
                return ERROR_TEST + n_line;
            line_output = output_reader.readLine();
            line_test = test_file_reader.readLine();
            n_line++;
        }
        return SUCCESS_TEST + n_line;
    }
}