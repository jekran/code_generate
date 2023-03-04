package com.cg.utils;

import com.cg.utils.entity.Table;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Date:    2016/2/1
 * Time:    17:55
 * details: 代码生成工具类
 */
public class CodeGenerateUtils {
    /**
     * @param rs ResultSet转list
     * @return
     * @throws SQLException
     */
    public static List resultSetToList(ResultSet rs) throws SQLException {
        if (rs == null) {
            return Collections.EMPTY_LIST;
        }
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData = new HashMap();
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 获取表信息
     *
     * @param table_name
     * @return
     */
    public static List<Table> getList(String path, String table_name, String database) {
        //赋值的list
        List<Table> list = new ArrayList<Table>();
        //数据库链接
        Connection conn = getDblink(path);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            //查询出表,列名,属性,注释
            String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.`COLUMNS` WHERE table_name = '" + table_name + "'  AND table_schema = '" + database + "'";
            //执行sql
            ResultSet rs = stmt.executeQuery(sql);
            List<Map<String, Object>> l = resultSetToList(rs);
            for (Map<String, Object> map : l) {
                //赋值
                Table table = new Table();
                //数据库无转换列名
                table.setTypeName(new String(String.valueOf(map.get("COLUMN_NAME")).getBytes(StandardCharsets.UTF_8)));
                //将列名全部改为小写,凡是加_首字母大写
                table.setColumnName(new String(StringUtils.uncapitalize(toUpper(String.valueOf(map.get("COLUMN_NAME")))).getBytes(StandardCharsets.UTF_8)));
                //将类名改为首字母大写
                table.setColumnNameUpper(new String(StringUtils.capitalize(table.getColumnName()).getBytes(StandardCharsets.UTF_8)));
                //设置sql的类型
                table.setDataType(new String(sqlType2JavaType(String.valueOf(map.get("DATA_TYPE"))).getBytes(StandardCharsets.UTF_8)));
                //设置注释
                table.setColumnComment(new String(String.valueOf(map.get("COLUMN_COMMENT")).getBytes(StandardCharsets.UTF_8)));
                list.add(table);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return list;
    }


    /**
     * 数据库链接
     * String MysqlUser = "root";
     * String password = "mysql";
     * String url = "jdbc:mysql://127.0.0.1:3306/eabs?useUnicode=true&characterEncoding=utf8";
     *
     * @return
     */

    public static Connection getDblink(String path) {
        Connection conn = null;
        //数据库连接
        try {
            //加载mysq驱动
            Class.forName("com.mysql.jdbc.Driver");
            PropertiesUtil propertiesUtil = PropertiesUtil.getMySqlProperties(path);
            conn = DriverManager.getConnection(propertiesUtil.getProperty("url"), propertiesUtil.getProperty("username"), propertiesUtil.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * “_”+小写 转成大写字母
     *
     * @param str
     * @return
     */
    public static String toUpper(String str) {
        String name = null;
        String[] split = StringUtils.split(str, "_");

        for (String string : split) {
            if (name == null) {
                name = StringUtils.capitalize(string);
            } else {
                name += StringUtils.capitalize(string);
            }
        }
        return name;
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    public static String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("Integer")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("double")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("variant")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "LocalDateTime";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        }

        return "String";
    }

    /**
     * 将内容写入文件
     *
     * @param content
     * @param filePath
     */
    public static void writeFile(String content, String filePath) {
        try {
            createFile(filePath);
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            System.err.println("文件 " + descFileName + " 已存在，覆盖生成");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            System.err.println(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                System.err.println("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                System.err.println(descFileName + " 文件创建成功!");
                return true;
            } else {
                System.err.println(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(descFileName + " 文件创建失败!");
            return false;
        }
    }

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new java.util.Date());
    }
}
