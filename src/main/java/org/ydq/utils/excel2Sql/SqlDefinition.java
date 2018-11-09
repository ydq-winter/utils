package org.ydq.utils.excel2Sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class SqlDefinition {

    @Value("${sql.definition}")
    private String definitionPath;

    public String[] loadDefinition() {
        File definition = new File(definitionPath);
        if (definition.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(definition));
                StringBuffer sql = new StringBuffer();
                String temp;
                while ((temp = reader.readLine()) != null) {
                    sql.append(temp);
                }
                String[] sqls = sql.toString().split("#");
                return sqls;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
