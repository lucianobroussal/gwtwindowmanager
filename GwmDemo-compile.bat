rem set the paths to the GWT libs in env.bat
call env
@java -cp "%~dp0\src;%~dp0\classes;%~dp0\src-samples;%GWT_HOME%/gwt-user.jar;%GWT_HOME%/gwt-dev-windows.jar;%GWT_HOME%/gwt-servlet.jar;lib/gwt-widgets-0.1.3.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\www" %* org.gwm.samples.gwmdemo.GwmDemo