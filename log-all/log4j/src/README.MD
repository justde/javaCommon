Log4j有三个主要组件：loggers，appender和layouts。使开发者能够根据消息类型和级别记录消息，并在运行时控制这些消息的格式和报告位置 。

loggers: 

1.配置根Logger
语法：log4j.rootLogger = [level] , appenderName, appenderName
其中，level 是日志记录的优先级，分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL或者自定义的级别。
优先级：ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF。上面文件设置的info级别，意思是大于等于info级别的日志都会输出。
常用的就是：ERROR、WARN、INFO、DEBUG。
appenderName指定日志信息输出位置，可同时指定多个输出目的地。譬如同时输出到控制台和输出到文本文件。

