# mybatis-generator-for-spring-plugin
基于mybatis generator生成spring 项目的架手架项目，自定义了一些插件

## 目前提供了4个插件
- JacksonAnnotationPlugin
- JacksonToJsonPlugin
- MapperAnnotationPlugin
- SpringServiceInterfacePlugin

## 如何测试和使用上面的插件
1. 找到resources文件夹中的generatorConfig.xml 1
2. 指定数据库jdbcConnection连接 2
3. 指定需要使用的插件 3
        <!--分页插件-->
        <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>
        <!--给Mapper增加@Mapper注解-->
        <plugin type="com.neo.mybatis.generator.plugin.MapperAnnotationPlugin"></plugin>
        <!--生成Spring Service 和 Service Impl插件-->
        <plugin type="com.neo.mybatis.generator.plugin.SpringServiceInterfacePlugin">
            <property name="targetDir" value="../mybatis-generator-plugin/src/main/java"></property>
            <property name="serviceInterfaceTargetPackage" value="com.ktb.service"></property>
            <property name="serviceImplTargetPackage" value="com.ktb.service.impl"></property>
        </plugin>
        <plugin type="com.neo.mybatis.generator.plugin.JacksonAnnotationPlugin"></plugin>

4. 修改测试 PluginTest文件，运行单元测试生成需要的代码 4
    ```java
    private File configFile;

    @Before
    public void beforetest(){
        configFile = new File("~/mybatis-generator-plugin/src/main/resources/generatorConfig.xml");
    }

    @Test
    public void testSpringServicePlugin() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    ```

### JacksonAnnotationPlugin插件
    该plugin是为生成的model类添加jackson的@JsonProperty、@JsonIgnore、 和@JsonFormat注解
### JacksonToJsonPlugin插件
    该plugin是为生成的model类添加toJson方法
### MapperAnnotationPlugin
    该plugin是为了给Springboot项目的mapper增加@Mapper注解
### SpringServiceInterfacePlugin
    生成spring service