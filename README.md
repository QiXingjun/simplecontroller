# simplecontroller

将JavaWeb实现简单的登录验证这篇文章中的示例进行修改。将其中的控制器部分改为使用配置文件来完成。还有一个小的修改，将登录的验证方式，从以用户名查询密码然后进行比较变为了通过用户名密码查询user，如果查出来就返回true，否则返回false。
具体来讲，dao层和service层的变化不大，只是登录验证方式进行简单的更改。将之前的Controller更改为全局的控制器，并采用配置文件的方式来完成。这个ActionController解析url，将不同的事情交给不同的Action去处理。比如，之前实现的是登录，这里解析出login请求后，就会交给LoginAction进行处理。LoginAction调用login()方法之后返回一个成功或者失败的flag，Controller根据返回的flag来进行结果的跳转。由于是采用配置文件的方式，还要建立一个解析配置文件的工具类ParseXml，根据配置文件的结构，还需要一个封装action节点的ActionXmlBean和一个封装视图的ResultXmlBean。

1. 收到http:localhost:8080/login.scaction的请求，解析出login。
2. 转发给LoginAction处理，LoginAction调用login()方法，这个方法执行完成之后返回一个returnFlag标志，success表示登录成功，fail表示登录失败。
3. ActionController根据返回的returnFlag判断跳转的页面，先查询配置文件（controller.xml），获取对应的跳转page，以及跳转的方式（转发或者重定向）。
4. 对于一个项目而言，要处理的请求有很多，也就说有很多的XxxAction那么对应的controller.xml文件就会很大，我们不会每次都来查找解析这个xml文件，而且对于需要同时管理很多信息时，常见的做法是把这若干信息封装到一些bean中。每个`<action>`封装到对应的ActionXmlBean类中，每个`<result>`封装到ResultXmlBean类中，因为每个`<action>`中包含若干的`<result>`所以每个ActionXmlBean总包含若干的`<Result>`。

具体见：[JavaWeb实现简单的登录验证(controller采用配置文件)](http://qixingjun.tech/2016/12/03/simple-controller-based-on-configuration-file/)
