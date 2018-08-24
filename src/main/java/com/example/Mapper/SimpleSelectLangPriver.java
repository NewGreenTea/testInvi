package com.example.Mapper;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleSelectLangPriver extends XMLLanguageDriver {
    private final static Pattern p=Pattern.compile("\\(#\\{(\\w+)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        //script  转化后sql语句
        Matcher matcher=p.matcher(script);

        if(matcher.find()){
            StringBuilder sb=new StringBuilder();
            sb.append("<where>");

            for(Field field:parameterType.getDeclaredFields()){
                String temp="<if test=\"_field !=null\"> and _column=#{_field}</if>";
                sb.append(temp.replaceAll("_field",field.getName())
                        .replaceAll("_column", CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,field.getName())));
            }

            sb.append("</where>");
            script=matcher.replaceAll(sb.toString());
            script="<script>"+script+"</script>";
            System.out.println(script+"-----------------------------------");
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}
