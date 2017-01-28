package com.neo.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by wenpeng on 2016/12/27.
 */
public class MapperAnnotationPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {

        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        interfaze.addAnnotation("@Mapper");
        return true;
    }
}
