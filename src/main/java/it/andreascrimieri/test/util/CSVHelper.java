package it.andreascrimieri.test.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import it.andreascrimieri.test.controller.dto.UserDTO;

import java.io.*;
import java.util.List;

public class CSVHelper {

    public static List<UserDTO> importFromCsv(InputStream inputStream) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));

        CsvToBean<UserDTO> csvToBean = new CsvToBeanBuilder(reader)
                .withType(UserDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }
}
