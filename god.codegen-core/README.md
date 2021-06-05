# CRUD 프로그램 자동 생성 기능

## 1. 구현도구(Implementation Tool) 소스코드 Version 3.10.0

```
C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.codegen.template\src\egovframework\dev\imp\codegen\template\model\DataModelContext.java

C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.codegen.template\src\egovframework\dev\imp\codegen\template\wizards\CodeGenTableWizardPage.java

C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.templates\src\main\java\model\DataModelContext.java

C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.templates\src\main\java\operation\CrudCodeGen.java

C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.templates\src\test\java\test\CrudCodeGenTest.java

C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.codegen.template.templates\eGovFrameTemplates
```

- egovframework.dev.imp.codegen.template
- egovframework.dev.imp.templates
- egovframework.dev.imp.codegen.template.templates

## 2. plugins

```
C:\EGOVFRAME-3.10.0\eGovFrameDev-3.10.0-64bit\eclipse\plugins\egovframework.dev.imp.codegen.template.templates_3.10.0.202102261503
```

- egovframework.dev.imp.codegen.template.templates_3.10.0.202102261503

C:\EGOVFRAME-3.10.0\eGovFrameDev-3.10.0-64bit\eclipse\plugins\egovframework.dev.imp.codegen.template.templates_3.10.0.202102261503\eGovFrameTemplates\crud
wizard.xml

```
author
createDate

DataAccess
    checkDataAccess

    sqlMapFolder
    mapperFolder
    daoPackage
    mapperPackage
    voPackage

Service
    checkService

    servicePackage
    implPackage

Web
    checkWeb

    controllerPackage
    jspFolder

```

- C:\Users\god\Downloads\egovframework.dev.imp.all-3.10.0-source\egovframework.dev.imp.codegen.template.templates\eGovFrameTemplates
- C:\EGOVFRAME-3.10.0\eGovFrameDev-3.10.0-64bit\eclipse\plugins\egovframework.dev.imp.codegen.template.templates_3.10.0.202102261503\eGovFrameTemplates

## 3. CodeGenTableWizardPage.java

/egovframework.dev.imp.codegen.template/src/egovframework/dev/imp/codegen/template/wizards/CodeGenTableWizardPage.java

```java
DataModelContext dataModel = new DataModelContext();
dataModel.setVender(vender);
dataModel.setDatabaseProductName(databaseProductName);

Entity entity = new Entity(table.getName());
dataModel.setEntity(entity);

List<Attribute> attributes = new ArrayList<Attribute>();
List<Attribute> pkAttributes = new ArrayList<Attribute>();
```
