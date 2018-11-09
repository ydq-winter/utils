package org.ydq.utils.excel2Sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class SqlFileWriter {

    @Value("${sql.file}")
    private String sqlFilePath;

    public void writeFile(List<String> sqls, String fileName) {
        File file = new File(sqlFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File sqlFile = new File(sqlFilePath + System.getProperty("file.separator") + fileName);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(sqlFile));
            for (String sql : sqls) {
                writer.write(sql);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
