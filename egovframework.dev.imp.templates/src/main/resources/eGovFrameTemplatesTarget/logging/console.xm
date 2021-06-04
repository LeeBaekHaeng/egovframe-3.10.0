<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/'>

	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d %5p [%c] %m%n" />
		</layout>
	</appender>
	
	<logger name="org.springframework" additivity="false">
		<level value="DEBUG" />
		<appender-ref ref="console" />
	</logger>
	
	<root>
		<level value="OFF" />
		<appender-ref ref="console" />
	</root>
		
</log4j:configuration>	