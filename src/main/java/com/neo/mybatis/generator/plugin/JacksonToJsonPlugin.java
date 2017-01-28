package com.neo.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by wenpeng on 2016/12/30.
 * 该plugin是为生成的model类添加toJson方法。toJson方法的实现依赖于jackson
 *
 * 生成后
 *
 * public class Account implements Serializable {
 public String toJson() throws IOException {
 ... ...
 }
 }
 */
public class JacksonToJsonPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setName("toJson");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addException(new FullyQualifiedJavaType("java.io.IOException"));
        method.addBodyLine("ObjectMapper mapper = new ObjectMapper();");
        method.addBodyLine("mapper.setTimeZone(TimeZone.getDefault());");
        method.addBodyLine("return mapper.writeValueAsString(this);");

        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.IOException"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.fasterxml.jackson.databind.ObjectMapper"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.TimeZone"));

        topLevelClass.addMethod(method);

        System.out.println("-----------------" + topLevelClass.getType().getShortName() + " add method=toJson implement by Jackson2.");
        return true;
    }

}
