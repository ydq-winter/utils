package org.ydq.utils.excel2Sql;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("excel2Sql.xml");
        SqlDefinition definition = context.getBean(SqlDefinition.class);
        String[] definitions = definition.loadDefinition();
        if (definitions == null || definitions.length == 0) {
            return;
        }
        ExcelLoader excelLoader = context.getBean(ExcelLoader.class);
        List<String> sqls = excelLoader.loadExcel(definitions);
        if (sqls != null && !sqls.isEmpty()) {
            SqlFileWriter writer = context.getBean(SqlFileWriter.class);
            writer.writeFile(sqls, "script.sql");
        }
    }
}
