# simplecontroller
1.收到http:localhost:8080/login.scaction的请求，解析出login。
2.转发给LoginAction处理，LoginAction调用login()方法，这个方法执行完成之后返回一个returnFlag标志，success表示登录成功，fail表示登录失败。
3.ActionController根据返回的returnFlag判断跳转的页面，先查询配置文件（controller.xml），获取对应的跳转page，以及跳转的方式（转发或者重定向）。
4.对于一个项目而言，要处理的请求有很多，也就说有很多的XxxAction那么对应的controller.xml文件就会很大，我们不会每次都来查找解析这个xml文件，而且对于需要同时管理很多信息时，常见的做法是把这若干信息封装到一些bean中。每个<action>封装到对应的ActionXmlBean类中，每个<result>封装到ResultXmlBean类中，因为每个<action>中包含若干的<result>所以每个ActionXmlBean总包含若干的<Result>。

