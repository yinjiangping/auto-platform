@echo off
rem 打包项目
"D:\Program Files\Java\jdk1.8.0_77\bin\java" "-Dmaven.home=D:\Program Files\WorkDir\apache-maven-3.1.0" "-Dclassworlds.conf=D:\Program Files\WorkDir\apache-maven-3.1.0\bin\m2.conf" -Didea.launcher.port=7534 "-Didea.launcher.bin.path=D:\Program Files\WorkDir\JetBrains\IntelliJ IDEA 13.1.4\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\WorkDir\apache-maven-3.1.0\boot\plexus-classworlds-2.4.2.jar;D:\Program Files\WorkDir\JetBrains\IntelliJ IDEA 13.1.4\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain org.codehaus.classworlds.Launcher -Didea.version=13.1.6 -s "D:\Program Files\WorkDir\apache-maven-3.1.0\myConfig\settings_new.xml" -DskipTests=true clean package

rem 进入jetty目录
cd D:\BatTool\jetty-distribution-9.3.0.v20150612\bin\

rem 停止jetty服务
rem for /f "tokens=5" %%a in ('netstat -ano ^| findstr 8080') do taskkill /f /pid %%i /t
sh jetty.sh stop

rem 复制应用到jetty工作目录
xcopy /y D:\Git_Work\auto-platform\target\autoPlatform.war D:\BatTool\jetty-distribution-9.3.0.v20150612\webapps\

rem 启动jetty服务
sh jetty.sh start

rem 回到项目目录
cd D:\Git_Work\auto-platform\


