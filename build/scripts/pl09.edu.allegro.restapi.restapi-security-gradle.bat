@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  pl09.edu.allegro.restapi.restapi-security-gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and PL09_EDU_ALLEGRO_RESTAPI_RESTAPI_SECURITY_GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\restapi-security-0.0.1-SNAPSHOT.jar;%APP_HOME%\lib\spring-boot-starter-jersey-1.2.8.RELEASE.jar;%APP_HOME%\lib\swagger-jersey2-jaxrs-1.5.0.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\spring-boot-starter-security-1.2.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-1.2.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.2.8.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.4.6.jar;%APP_HOME%\lib\hibernate-validator-5.1.3.Final.jar;%APP_HOME%\lib\spring-core-4.1.9.RELEASE.jar;%APP_HOME%\lib\spring-web-4.1.9.RELEASE.jar;%APP_HOME%\lib\jersey-server-2.14.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.14.jar;%APP_HOME%\lib\jersey-container-servlet-2.14.jar;%APP_HOME%\lib\jersey-bean-validation-2.14.jar;%APP_HOME%\lib\jersey-spring3-2.14.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.14.jar;%APP_HOME%\lib\swagger-jaxrs-1.5.0.jar;%APP_HOME%\lib\jersey-media-multipart-2.1.jar;%APP_HOME%\lib\spring-beans-4.1.9.RELEASE.jar;%APP_HOME%\lib\spring-context-4.1.9.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.1.9.RELEASE.jar;%APP_HOME%\lib\spring-security-config-3.2.9.RELEASE.jar;%APP_HOME%\lib\spring-security-web-3.2.9.RELEASE.jar;%APP_HOME%\lib\spring-aop-4.1.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-1.2.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.2.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.2.8.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.14.jar;%APP_HOME%\lib\tomcat-embed-core-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-el-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-logging-juli-8.0.30.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.0.30.jar;%APP_HOME%\lib\jackson-core-2.4.6.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.1.3.GA.jar;%APP_HOME%\lib\classmate-1.0.0.jar;%APP_HOME%\lib\jersey-common-2.14.jar;%APP_HOME%\lib\jersey-client-2.14.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.4.0-b06.jar;%APP_HOME%\lib\javax.inject-2.4.0-b06.jar;%APP_HOME%\lib\hk2-locator-2.4.0-b06.jar;%APP_HOME%\lib\hk2-2.4.0-b06.jar;%APP_HOME%\lib\spring-bridge-2.4.0-b06.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.4.2.jar;%APP_HOME%\lib\jackson-dataformat-xml-2.4.2.jar;%APP_HOME%\lib\swagger-core-1.5.0.jar;%APP_HOME%\lib\reflections-0.9.9.jar;%APP_HOME%\lib\mimepull-1.8.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\spring-security-core-3.2.9.RELEASE.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.13.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.13.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.13.jar;%APP_HOME%\lib\logback-classic-1.1.3.jar;%APP_HOME%\lib\jersey-guava-2.14.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.4.0-b06.jar;%APP_HOME%\lib\aopalliance-repackaged-2.4.0-b06.jar;%APP_HOME%\lib\config-types-2.4.0-b06.jar;%APP_HOME%\lib\core-2.4.0-b06.jar;%APP_HOME%\lib\hk2-config-2.4.0-b06.jar;%APP_HOME%\lib\hk2-runlevel-2.4.0-b06.jar;%APP_HOME%\lib\class-model-2.4.0-b06.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.4.2.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\commons-lang3-3.2.1.jar;%APP_HOME%\lib\jackson-datatype-joda-2.4.2.jar;%APP_HOME%\lib\swagger-models-1.5.0.jar;%APP_HOME%\lib\annotations-2.0.1.jar;%APP_HOME%\lib\logback-core-1.1.3.jar;%APP_HOME%\lib\tiger-types-1.4.jar;%APP_HOME%\lib\bean-validator-2.4.0-b06.jar;%APP_HOME%\lib\asm-all-repackaged-2.4.0-b06.jar;%APP_HOME%\lib\joda-time-2.2.jar;%APP_HOME%\lib\swagger-annotations-1.5.0.jar;%APP_HOME%\lib\jackson-annotations-2.4.2.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.4.2.jar;%APP_HOME%\lib\javassist-3.18.2-GA.jar;%APP_HOME%\lib\slf4j-api-1.7.13.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.4.2.jar

@rem Execute pl09.edu.allegro.restapi.restapi-security-gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %PL09_EDU_ALLEGRO_RESTAPI_RESTAPI_SECURITY_GRADLE_OPTS%  -classpath "%CLASSPATH%" pl.amu.service.UsersApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable PL09_EDU_ALLEGRO_RESTAPI_RESTAPI_SECURITY_GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%PL09_EDU_ALLEGRO_RESTAPI_RESTAPI_SECURITY_GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
